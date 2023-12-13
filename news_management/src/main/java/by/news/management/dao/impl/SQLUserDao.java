package by.news.management.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.news.management.dao.UserDao;
import by.news.management.dao.exceptions.DAOException;
import by.news.management.dao.exceptions.UserNotFoundException;
import by.news.management.dao.connection.ConnectionPool;
import by.news.management.dao.connection.ConnectionPoolException;
import by.news.management.bean.User;

public class SQLUserDao implements UserDao {
	private final String INSERT_USER = "INSERT INTO users (name,surname,email,login,password,status) VALUES (?,?,?,?,?,?)";
	private final String SELECT_ID_FROM_ROLES = "SELECT id FROM roles WHERE title = ?";
	private final String INSERT_INTO_USERS_HAS_ROLES = "INSERT INTO users_has_roles (Users_id,roles_id) VALUES (?,?)";
	private final String SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ? AND status = ?";
	private final String SELECT_LIST_OF_USERS = "SELECT * FROM users WHERE status = ? LIMIT ?";
	private final String UPDATE_USER = "UPDATE users SET name = ?, surname = ?, email = ?, password = ? WHERE login = ?";
	private final String DELETE_USER = "UPDATE users SET status = ? WHERE login = ?";
	private final String BLOCK_USER = "UPDATE users SET status = ? WHERE id = ?";

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void registration(User user) throws DAOException {
		Connection connection = null;
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			
			insertUserMethod(connection, user);
			insertUsersHasRolesMethod(connection, user, selectRoleId(connection, user));
			
			connection.commit();
			
		} catch (SQLException | ConnectionPoolException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new DAOException("rollback eror", e);
			}
			throw new DAOException("Registration error", e);
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException e) {
					throw new DAOException("rollback error", e);
				}
			}
		}
	}

	@Override
	public User signIn(String login, String password) throws DAOException, UserNotFoundException {
		ResultSet resultSet = null;
		User user = new User();
		try (PreparedStatement preparedStatement = connectionPool.takeConnection()
				.prepareStatement(SELECT_USER_BY_LOGIN)) {

			preparedStatement.setString(1, login);
			preparedStatement.setString(2, "active");
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				throw new UserNotFoundException("User is not exist");
			}

			if (!PasswordSecurity.passwordCheck(password, resultSet.getString(6))) {
				throw new DAOException("Wrong password");
			}
			user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname"));
			return user;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("Authentication error", e);
		}
	}

	@Override
	public List<User> getListOfUsers(int quantity) throws DAOException {
		ResultSet resultSet = null;
		try (PreparedStatement preparedStatement = connectionPool.takeConnection()
				.prepareStatement(SELECT_LIST_OF_USERS)) {
			List<User> userList = new ArrayList<>();
			preparedStatement.setString(1, "active");
			preparedStatement.setInt(2, quantity);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				userList.add(
						new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname")));
			}
			return userList;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public User getByLogin(String login) throws DAOException {
		ResultSet resultSet = null;
		try (PreparedStatement preparedStatement = connectionPool.takeConnection()
				.prepareStatement(SELECT_USER_BY_LOGIN)) {
			preparedStatement.setString(1, login);

			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				throw new DAOException("User is not exist");
			}
			User user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname"),
					resultSet.getString("email"));
			return user;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public User updateUser(User user) throws DAOException {
		try (PreparedStatement preparedStatement = connectionPool.takeConnection().prepareStatement(UPDATE_USER)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getSurname());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, PasswordSecurity.hashPassword(user.getPassword()));
			preparedStatement.setString(5, user.getLogin());

			int result = preparedStatement.executeUpdate();
			if (result == 0) {
				throw new DAOException("Error with update");
			}
			return user;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("Error in process of update user", e);
		}
	}

	@Override
	public boolean deleteUser(User user) throws DAOException {
		boolean status = true;
		try (PreparedStatement preparedStatement = connectionPool.takeConnection().prepareStatement(DELETE_USER)) {
			preparedStatement.setString(1, "inactive");
			preparedStatement.setInt(2, user.getId());

			int result = preparedStatement.executeUpdate();
			if (result == 0) {
				throw new DAOException("Error with delete");
			}
			return status;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("Error in process of delete user", e);
		}
	}

	@Override
	public boolean blockUser(User user) throws DAOException {
		boolean status = true;
		try (PreparedStatement preparedStatement = connectionPool.takeConnection().prepareStatement(BLOCK_USER)) {
			preparedStatement.setString(1, "block");
			preparedStatement.setInt(2, user.getId());

			int result = preparedStatement.executeUpdate();
			if (result == 0) {
				throw new DAOException("Error with block");
			}
			return status;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("Error in process of block user", e);
		}
	}

	private User insertUserMethod(Connection connection, User user) throws DAOException {
		try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER,
				Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getSurname());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getLogin());
			preparedStatement.setString(5, PasswordSecurity.hashPassword(user.getPassword()));
			preparedStatement.setObject(6, String.valueOf(user.getStatus()));
			int result = preparedStatement.executeUpdate();
			if (result == 0) {
				throw new DAOException("Error adding a new user");
			}
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			user.setId(rs.getInt(1));

			return new User(rs.getInt(1), user.getName(), user.getSurname());

		} catch (SQLException e) {
			throw new DAOException("Registration error", e);
		}
	}

	private boolean insertUsersHasRolesMethod(Connection connection, User user, int id) throws DAOException, SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS_HAS_ROLES);
		preparedStatement.setInt(1, user.getId());
		preparedStatement.setInt(2, id);
		
		int result = preparedStatement.executeUpdate();
		if (result == 0) {
			throw new DAOException("Error adding information to users_has_roles");
		}
		
		preparedStatement.close();
		return true;
	}

	private int selectRoleId(Connection connection, User user) throws DAOException, SQLException {
		int roleId = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID_FROM_ROLES);
		preparedStatement.setObject(1, String.valueOf(user.getRoles()));
		ResultSet resultSet = preparedStatement.executeQuery();

		if (!resultSet.next()) {
			throw new DAOException("Role is not exist");
		}
		roleId = resultSet.getInt("id");

		preparedStatement.close();
		resultSet.close();

		return roleId;

	}
}
