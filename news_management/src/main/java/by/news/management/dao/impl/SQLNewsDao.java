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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.news.management.dao.NewsDao;
import by.news.management.dao.connection.ConnectionPool;
import by.news.management.dao.connection.ConnectionPoolException;
import by.news.management.dao.exceptions.DAOException;
import by.news.management.bean.News;

public class SQLNewsDao implements NewsDao {
	private final static Logger log = LogManager.getRootLogger();

	private static final String COLUMN_ID = "id";
	private static final String COLUMN_TITLE = "title";
	private static final String COLUMN_DATE = "publication_date";
	private static final String COLUMN_BRIEF = "brief";
	private static final String COLUMN_CONTENT = "content";
	private static final String COLUMN_STATUS = "status";
	private static final String COLUMN_USERS_ID = "Users_id";

	private final String INSERT_NEWS = "INSERT INTO news (title,publication_date,brief,content,status,Users_id) VALUES (?,?,?,?,?,?)";
	private final String EDIT_NEWS = "UPDATE news SET title = ?, brief = ?, content = ?  where id = ? ";
	private final String INSERT_INTO_NEWS_UPDATE = "INSERT INTO news_updated (update_date,old_text,status,users_id,news_id) VALUES (?,?,?,?,?)";
	private final String DELETE_NEWS = "UPDATE news SET status=? WHERE id = ?";
	private final String SELECT_NEWS_BY_TITLE = "SELECT * FROM news WHERE title = ?";
	private final String SELECT_NEWS_BY_DATE = "SELECT * FROM news WHERE publication_date = ?";
	private final String SELECT_NEWS_BY_ID = "SELECT * FROM news WHERE id = ?";
	private final String SELECT_NEWS_TO_PAGE_LIMIT = "SELECT * FROM news WHERE status = ? LIMIT ? OFFSET ?";
	private final String SELECT_NUMBER_OF_ACTIVE_NEWS = "SELECT COUNT(*) FROM news WHERE status = ?";

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public int addNews(News news) throws DAOException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEWS,
						Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, news.getTitle());
			preparedStatement.setDate(2, news.getDate());
			preparedStatement.setString(3, news.getBrief());
			preparedStatement.setString(4, news.getContent());
			preparedStatement.setString(5, news.getStatus());
			preparedStatement.setInt(6, news.getUserId());

			int result = preparedStatement.executeUpdate();
			if (result == 0) {
				log.error("Error of insert news");
				throw new DAOException(
						"Error in process of inserting information to news in DataBase, news was not add");
			}
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			news.setId(rs.getInt(1));
			return rs.getInt(1);
		} catch (SQLException e) {
			log.error("ERROR", e);
			throw new DAOException("Error in process of adding news, news was not add because of SQLException", e);
		} catch (ConnectionPoolException e1) {
			log.error("ERROR", e1);
			throw new DAOException(
					"Error in process of adding news, news was not add because of ConnectionPoolException", e1);
		}
	}

	@Override
	public void editNews(News news, int userId) throws DAOException {
		Connection connection = null;
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			News oldNews = getNewsById(connection, news.getId());
			updateNews(connection, news);
			insertNewsUdpates(connection, oldNews, news, userId);
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				log.error("ERROR", e);
				throw new DAOException("Rollback error", e);
			}
			log.error("ERROR", e);
			throw new DAOException(
					"Error in process of updating information in database after edit news, commit was not comleted, trying to do rollback",
					e);
		} catch (ConnectionPoolException e) {
			log.error("ERROR", e);
			throw new DAOException(
					"Error in process of updating information in database after edit news because of ConnectionPoolException",
					e);
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException e) {
					log.error("ERROR", e);
					throw new DAOException("Error in process of closing connection", e);
				}
			}
		}
	}

	@Override
	public void deleteNews(int id) throws DAOException {
		try (PreparedStatement preparedStatement = connectionPool.takeConnection().prepareStatement(DELETE_NEWS)) {
			preparedStatement.setString(1, "in trash");
			preparedStatement.setInt(2, id);
			int result = preparedStatement.executeUpdate();
			if (result == 0) {
				log.error("Error in process of deleting news");
				throw new DAOException("Invalid information, the process of executeUpdate was not completed");
			}
		} catch (SQLException e) {
			log.error("ERROR", e);
			throw new DAOException("Error: information in dababase was not updatetd, news was not deleted", e);
		} catch (ConnectionPoolException e1) {
			log.error("ERROR", e1);
			throw new DAOException(
					"Error: information in dababase was not updatetd, news was not deleted because of ConnectionPoolException",
					e1);
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
				log.error("Error in method getByTitle()");
				throw new DAOException("Error in process of executeQuery, resultSet is null");
			}
			News news = new News(resultSet.getDate(COLUMN_DATE), resultSet.getString(COLUMN_TITLE),
					resultSet.getNString(COLUMN_BRIEF), resultSet.getString(COLUMN_CONTENT),
					resultSet.getString(COLUMN_STATUS), resultSet.getInt(COLUMN_USERS_ID), resultSet.getInt(COLUMN_ID));
			return news;
		} catch (SQLException e) {
			log.error("ERROR", e);
			throw new DAOException("Error in process of getting news from database by title because of SQLException",
					e);

		} catch (ConnectionPoolException e1) {
			log.error("ERROR", e1);
			throw new DAOException(
					"Error in process of getting news from database by title because of ConnectionPoolException", e1);
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
				log.error("Error in method getByDate()");
				throw new DAOException("Error in process of executeQuery, resultSet is null");
			}
			News news = new News(resultSet.getDate(COLUMN_DATE), resultSet.getString(COLUMN_TITLE),
					resultSet.getNString(COLUMN_BRIEF), resultSet.getString(COLUMN_CONTENT),
					resultSet.getString(COLUMN_STATUS), resultSet.getInt(COLUMN_USERS_ID), resultSet.getInt(COLUMN_ID));
			return news;
		} catch (SQLException e) {
			log.error("ERROR", e);
			throw new DAOException("Error in process of getting news from database by date because of SQLException", e);
		} catch (ConnectionPoolException e1) {
			log.error("ERROR", e1);
			throw new DAOException(
					"Error in process of getting news from database by date because of ConnectionPoolException", e1);
		}
	}

	@Override
	public News getById(int id) throws DAOException {
		ResultSet resultSet = null;
		try (PreparedStatement preparedStatement = connectionPool.takeConnection()
				.prepareStatement(SELECT_NEWS_BY_ID)) {
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				log.error("Error in method getById()");
				throw new DAOException("News with this id was not found");
			}
			News news = new News(resultSet.getDate(COLUMN_DATE), resultSet.getString(COLUMN_TITLE),
					resultSet.getNString(COLUMN_BRIEF), resultSet.getString(COLUMN_CONTENT),
					resultSet.getString(COLUMN_STATUS), resultSet.getInt(COLUMN_USERS_ID), resultSet.getInt(COLUMN_ID));

			return news;
		} catch (SQLException e) {
			log.error("ERROR", e);
			throw new DAOException("Error in process of getting news from database by id because of SQLException", e);
		} catch (ConnectionPoolException e1) {
			log.error("ERROR", e1);
			throw new DAOException(
					"Error in process of getting news from database by id because of ConnectionPoolException", e1);
		}
	}

	@Override
	public List<News> getListOfNews(int page) throws DAOException {
		int offset;
		if (page != 1) {
			offset = (page - 1) * 10;
		} else {
			offset = 0;
		}

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NEWS_TO_PAGE_LIMIT);) {
			preparedStatement.setString(1, "active");
			preparedStatement.setInt(2, 10);
			preparedStatement.setInt(3, offset);
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				if (!resultSet.next()) {
					log.error("Error in method getListOfNews()");
					throw new DAOException("News in range was not found");
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
		} catch (SQLException e) {
			log.error("ERROR", e);
			throw new DAOException("Error in process of getting news from database because of SQLException", e);
		} catch (ConnectionPoolException e1) {
			log.error("ERROR", e1);
			throw new DAOException("Error in process of getting news from database because of ConnectionPoolException",
					e1);
		}
	}

	@Override
	public List<Integer> getListOfPages() throws DAOException {
		ResultSet resultSet = null;
		try (PreparedStatement preparedStatement = connectionPool.takeConnection()
				.prepareStatement(SELECT_NUMBER_OF_ACTIVE_NEWS)) {
			preparedStatement.setString(1, "active");
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				log.error("Error in method getListOfPages()");
				throw new DAOException("News in range was not found");
			}
			int countOfNews = resultSet.getInt(1);

			List<Integer> pages = new ArrayList<Integer>();
			int numberOfPages;

			if (countOfNews % 10 == 0) {
				numberOfPages = countOfNews / 10;
				System.out.println(pages);
			} else {
				numberOfPages = countOfNews / 10 + 1;
			}

			for (int i = 1; i <= numberOfPages; i++) {
				pages.add(i);
			}
			return pages;
		} catch (SQLException e) {
			log.error("ERROR", e);
			throw new DAOException("Error", e);
		} catch (ConnectionPoolException e1) {
			log.error("ERROR", e1);
			throw new DAOException("Error", e1);
		}
	}

	private News getNewsById(Connection connection, int id) throws DAOException {
		ResultSet resultSet = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NEWS_BY_ID)) {
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				log.error("Error in method getNewsById()");
				throw new DAOException("News with this id was not found");
			}
			News news = new News(resultSet.getDate(COLUMN_DATE), resultSet.getString(COLUMN_TITLE),
					resultSet.getNString(COLUMN_BRIEF), resultSet.getString(COLUMN_CONTENT),
					resultSet.getString(COLUMN_STATUS), resultSet.getInt(COLUMN_USERS_ID), resultSet.getInt(COLUMN_ID));
			return news;
		} catch (SQLException e) {
			log.error("ERROR", e);
			throw new DAOException("Error in process of selecting news by id", e);
		}
	}

	private void updateNews(Connection connection, News news) throws DAOException, ConnectionPoolException {
		try (PreparedStatement preparedStatement = connection.prepareStatement(EDIT_NEWS)) {
			preparedStatement.setString(1, news.getTitle());
			preparedStatement.setString(2, news.getBrief());
			preparedStatement.setString(3, news.getContent());
			preparedStatement.setInt(4, news.getId());

			int result = preparedStatement.executeUpdate();
			if (result == 0) {
				log.error("Error in method updateNews()");
				throw new DAOException("Information was not updated");
			}

		} catch (SQLException e) {
			log.error("ERROR", e);
			throw new DAOException("Error in process of updating information of news", e);
		}
	}

	private void insertNewsUdpates(Connection connection, News oldNews, News news, int userId)
			throws SQLException, DAOException {
		try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_NEWS_UPDATE)) {
			preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
			preparedStatement.setString(2, oldNews.getContent());
			preparedStatement.setString(3, news.getStatus());
			preparedStatement.setInt(4, userId);
			preparedStatement.setInt(5, news.getId());

			int result = preparedStatement.executeUpdate();
			if (result == 0) {
				log.error("Error in method insertNewsUdpates()");
				throw new DAOException("Information in table news_updatetd was not updated");
			}

		} catch (SQLException e) {
			log.error("ERROR", e);
			throw new DAOException("Error in process of inserting information to table(news_updated)", e);
		}
	}
}
