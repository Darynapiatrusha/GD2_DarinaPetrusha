package by.news.management.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import by.news.management.dao.NewsDao;
import by.news.management.dao.connection.ConnectionPool;
import by.news.management.dao.connection.ConnectionPoolException;
import by.news.management.dao.exceptions.DAOException;
import by.news.management.bean.News;

public class SQLNewsDao implements NewsDao {
	private final String INSERT_NEWS = "INSERT INTO news (title,publication_date,brief,content,status,Users_id) VALUES (?,?,?,?,?,?)";
	private final String EDIT_NEWS = "UPDATE news SET (title,brief,content,status) VALUES (?,?,?,?)";
	private final String INSERT_INTO_NEWS_UPDATE = "INSERT INTO news_update (update_date,old_text,status,user_id,news_id) VALUES (?,?,?,?,?)";
	private final String DELETE_NEWS = "UPDATE news SET status VALUES ? WHERE title = ?";
	private final String SELECT_NEWS_BY_TITLE = "SELECT * FROM news WHERE title = ?";
	private final String SELECT_NEWS_BY_DATE = "SELECT * FROM news WHERE publication_date = ?";
	private final String SELECT_NEWS_BY_ID = "SELECT * FROM news WHERE id = ?";
	private final String SELECT_NEWS_FROM_TO_ID = "SELECT * FROM news WHERE id between ? and ?";

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public boolean addNews(News news) throws DAOException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEWS,Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, news.getTitle());
			preparedStatement.setDate(2, news.getDate());
			preparedStatement.setString(3, news.getBrief());
			preparedStatement.setString(4, news.getContent());
			preparedStatement.setString(5, news.getStatus());
			preparedStatement.setInt(6, news.getUserId());

			int result = preparedStatement.executeUpdate();
			if (result == 0) {
				throw new DAOException("Error adding news");
			}
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			news.setId(rs.getInt(1));
			return true;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("Error in process of adding news", e);
		}
	}

	@Override
	public boolean editNews(News news) throws DAOException {
		try (PreparedStatement preparedStatement = connectionPool.takeConnection().prepareStatement(SELECT_NEWS_BY_ID);
				PreparedStatement preparedSt = connectionPool.takeConnection().prepareStatement(EDIT_NEWS);
				PreparedStatement preparedStmnt = connectionPool.takeConnection()
						.prepareStatement(INSERT_INTO_NEWS_UPDATE)) {
			preparedStatement.setInt(1, news.getUserId());
			ResultSet rs = preparedStatement.executeQuery();

			Date date = Date.valueOf(LocalDate.now());
			if (!rs.next()) {
				throw new DAOException("Error in process of getting old news");
			}

			preparedStmnt.setDate(1, date);
			preparedSt.setString(2, rs.getString(5));
			preparedSt.setString(3, rs.getString(6));
			preparedSt.setString(4, rs.getString(7));
			preparedSt.setString(5, rs.getString(1));

			int result = preparedStmnt.executeUpdate();
			if (result == 0) {
				throw new DAOException("Error editing news");
			}
			preparedSt.setString(1, news.getTitle());
			preparedSt.setString(2, news.getBrief());
			preparedSt.setString(3, news.getContent());
			preparedSt.setString(4, news.getStatus());

			int res = preparedSt.executeUpdate();
			if (res == 0) {
				throw new DAOException("Error editing news");
			}

			return true;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("Error in process of editing news", e);
		}
	}

	@Override
	public boolean deleteNews(int id) throws DAOException {
		try (PreparedStatement preparedStatement = connectionPool.takeConnection().prepareStatement(DELETE_NEWS)) {
			preparedStatement.setString(1, "in trash");
			preparedStatement.setInt(2, id);
			int result = preparedStatement.executeUpdate();
			if (result == 0) {
				throw new DAOException("Error");
			}
			return true;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("Error", e);
		}
	}

	@Override
	public News getByTitle(String title) throws DAOException {
		ResultSet resultSet = null;
		try (PreparedStatement preparedStatement = connectionPool.takeConnection()
				.prepareStatement(SELECT_NEWS_BY_TITLE)) {
			preparedStatement.setString(1, title);
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				throw new DAOException("Error");
			}
			News news = new News(resultSet.getDate("publication_date"), resultSet.getString("title"),
					resultSet.getNString("brief"), resultSet.getString("content"), resultSet.getString("status"),
					resultSet.getInt("Users_id"));
			return news;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("Error", e);

		}
	}

	@Override
	public News getByDate(Date date) throws DAOException {
		ResultSet resultSet = null;
		try (PreparedStatement preparedStatement = connectionPool.takeConnection()
				.prepareStatement(SELECT_NEWS_BY_DATE)) {
			preparedStatement.setDate(1, date);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				throw new DAOException("Error");
			}
			News news = new News(resultSet.getDate("publication_date"), resultSet.getString("title"),
					resultSet.getNString("brief"), resultSet.getString("content"), resultSet.getString("status"),
					resultSet.getInt("Users_id"));
			return news;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("Error", e);
		}
	}

	@Override
	public News getById(int id) throws DAOException {
		ResultSet resultSet = null;
		try (PreparedStatement preparedStatement = connectionPool.takeConnection().prepareStatement(SELECT_NEWS_BY_ID)) {
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				throw new DAOException("Error");
			}
			News news = new News(resultSet.getDate("publication_date"), resultSet.getString("title"),
					resultSet.getNString("brief"), resultSet.getString("content"), resultSet.getString("status"),
					resultSet.getInt("Users_id"));
			return news;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("Error", e);
		}
	}

	@Override
	public List<News> getListOfNews(int fromId, int toId) throws DAOException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NEWS_FROM_TO_ID);) {

			preparedStatement.setInt(1, fromId);
			preparedStatement.setInt(2, toId);
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				if (!resultSet.next()) {
					throw new DAOException("News in range not found");
				}

				List<News> newsList = new ArrayList<>();
				do {
					News news = new News();
					news.setId(resultSet.getInt(1));
					news.setTitle(resultSet.getString(2));
					news.setDate(resultSet.getDate(3));
					news.setBrief(resultSet.getString(4));
					news.setContent(resultSet.getString(5));
					news.setStatus(resultSet.getString(6));
					news.setUserId(resultSet.getInt(7));

					newsList.add(news);
				} while (resultSet.next());

				return newsList;
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("Error in the news getting process", e);
		}
	}
}
