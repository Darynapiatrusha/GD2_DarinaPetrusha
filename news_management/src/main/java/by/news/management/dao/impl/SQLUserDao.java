package by.news.management.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.news.management.dao.DAOException;
import by.news.management.dao.NoSuchUserException;
import by.news.management.dao.UserDao;
import by.news.management.bean.User;

public class SQLUserDao implements UserDao {

	@Override
	public boolean registration(User user) throws DAOException {

		PreparedStatement preparedStatement = null;
		final String insert = "INSERT INTO users (name,surname,email,login,password,status, userId) VALUES (?,?,?,?,?,?)";
		try {
			preparedStatement = DBConnection.getDBConnection().prepareStatement(insert);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getSurname());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getLogin());
			preparedStatement.setString(5, BcryptSecurity.hashPassword(user.getPassword()));
			preparedStatement.setString(6, user.getStatus());

			int result = preparedStatement.executeUpdate();
			if (result == 0) {
				throw new DAOException("Error adding a new user");
			}
			return true;
		} catch (SQLException e) {
			throw new DAOException("Error in proccess of user`s registation");
		}
	}

	@Override
	public boolean signIn(String login, String password) throws DAOException, NoSuchUserException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		final String select = "SELECT * FROM users WHERE login = ?";

		try {
			preparedStatement = DBConnection.getDBConnection().prepareStatement(select);
			preparedStatement.setString(1, login);

			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				throw new NoSuchUserException("User is not exist");
			}
			if (!BcryptSecurity.passwordCheck(password, resultSet.getString(6))) {
				throw new DAOException("Wrong password");
			}
			return true;
		} catch (SQLException e) {
			throw new DAOException("Authentication error");
		}
	}

	@Override
	public List<User> getListOfUsers(int quantity) throws DAOException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		final String select = "SELECT * FROM users WHERE status = ? LIMIT ?";
		try {
			preparedStatement = DBConnection.getDBConnection().prepareStatement(select);
			List<User> userList = new ArrayList<>();
			preparedStatement.setString(1, "active");
			preparedStatement.setInt(2, quantity);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				userList.add(new User(resultSet.getString("name"), resultSet.getString("surname"),
						resultSet.getString("login"), resultSet.getString("email"), resultSet.getString("password"),
						resultSet.getString("status")));
			}
			return userList;
		} catch (SQLException e) {
			throw new DAOException("Error");
		}
	}

	@Override
	public User getByLogin(String login) throws DAOException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		final String select = "SELECT * FROM users WHERE login = ?";
		try {
			preparedStatement = DBConnection.getDBConnection().prepareStatement(select);
			preparedStatement.setString(1, login);

			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				throw new DAOException("User is not exist");
			}
			User user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getString(5),
					resultSet.getString(4), resultSet.getString(6), resultSet.getString(7));
			return user;
		} catch (SQLException e) {
			throw new DAOException("Error");
		}
	}

	@Override
	public User updateUser(User user) throws DAOException {
		PreparedStatement preparedStatement = null;
		final String update = "UPDATE users SET name = ?, surname = ?, email = ?, password = ? WHERE login = ?";
		try {
			preparedStatement = DBConnection.getDBConnection().prepareStatement(update);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getSurname());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, BcryptSecurity.hashPassword(user.getPassword()));
			preparedStatement.setString(5, user.getLogin());

			int result = preparedStatement.executeUpdate();
			if (result == 0) {
				throw new DAOException("Error with update");
			}
			return user;
		} catch (SQLException e) {
			throw new DAOException("Error");
		}
	}

	@Override
	public boolean deleteUser(User user) throws DAOException {
		PreparedStatement preparedStatement = null;
		final String update = "UPDATE users SET status = ? WHERE login = ?";
		boolean status = true;
		try {
			preparedStatement = DBConnection.getDBConnection().prepareStatement(update);
			preparedStatement.setString(1, "inactive");
			preparedStatement.setString(2, user.getLogin());

			int result = preparedStatement.executeUpdate();
			if (result == 0) {
				throw new DAOException("Error with update");
			}
			return status;
		} catch (SQLException e) {
			throw new DAOException("Error");
		}
	}
}
