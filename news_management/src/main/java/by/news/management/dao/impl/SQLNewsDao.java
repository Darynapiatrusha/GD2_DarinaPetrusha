package by.news.management.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import by.news.management.dao.DAOException;
import by.news.management.dao.NewsDao;
import by.news.management.bean.News;
import by.news.management.bean.User;

public class SQLNewsDao implements NewsDao {

	@Override
	public boolean addNews(News news, User user) throws DAOException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		final String select = "SELECT * FROM users WHERE login = ?";
		final String insert = "INSERT INTO news (title,publication_date,brief,content,status,Users_id) VALUES (?,?,?,?,?,?)";
		try {
			preparedStatement = DBConnection.getDBConnection().prepareStatement(select);
			preparedStatement.setString(1, user.getLogin());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				preparedStatement = DBConnection.getDBConnection().prepareStatement(insert);
				preparedStatement.setString(1, news.getTitle());
				preparedStatement.setDate(2, news.getDate());
				preparedStatement.setString(3, news.getBrief());
				preparedStatement.setString(4, news.getContent());
				preparedStatement.setString(5, news.getStatus());
				preparedStatement.setInt(6, resultSet.getInt(1));
				int result = preparedStatement.executeUpdate();
				if (result == 0) {
					throw new DAOException("Error adding new news");
				}
			}
			return true;
		} catch (SQLException e) {
			throw new DAOException("Error");
		}
	}

	@Override
	public boolean editNews(News news) throws DAOException {
		return true;
	}

	@Override
	public boolean deleteNews(News news) throws DAOException {
		PreparedStatement preparedStatement = null;
		final String update = "UPDATE news SET status VALUES ? WHERE title = ?";
		try {
			preparedStatement = DBConnection.getDBConnection().prepareStatement(update);
			preparedStatement.setString(1, "in trash");
			preparedStatement.setString(2, news.getTitle());
			int result = preparedStatement.executeUpdate();
			if (result == 0) {
				throw new DAOException("Error");
			}
			return true;
		} catch (SQLException e) {
			throw new DAOException("Error");
		}
	}

	@Override
	public News getByTitle(String title) throws DAOException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		final String select = "SELECT * FROM news WHERE title = ?";
		try {
			preparedStatement = DBConnection.getDBConnection().prepareStatement(select);
			preparedStatement.setString(1, title);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				throw new DAOException("Error");
			}
			News news = new News(resultSet.getDate("publication_date"), resultSet.getString("title"),
					resultSet.getNString("brief"), resultSet.getString("content"), resultSet.getString("status"),
					resultSet.getInt("Users_id"));
			return news;
		} catch (SQLException e) {
			throw new DAOException("Error");
		}
	}

	@Override
	public News getByDate(Date date) throws DAOException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		final String select = "SELECT * FROM news WHERE publication_date = ?";
		try {
			preparedStatement = DBConnection.getDBConnection().prepareStatement(select);
			preparedStatement.setDate(1, date);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				throw new DAOException("Error");
			}
			News news = new News(resultSet.getDate("publication_date"), resultSet.getString("title"),
					resultSet.getNString("brief"), resultSet.getString("content"), resultSet.getString("status"),
					resultSet.getInt("Users_id"));
			return news;
		} catch (SQLException e) {
			throw new DAOException("Error");
		}
	}

	@Override
	public List<News> getListOfNews(int quantity) throws DAOException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		final String select = "SELECT * FROM news WHERE status = ? LIMIT ?";
		try {
			preparedStatement = DBConnection.getDBConnection().prepareStatement(select);
			List<News> newsList = new ArrayList<>();
			preparedStatement.setString(1, "active");
			preparedStatement.setInt(2, quantity);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				newsList.add(new News(resultSet.getDate("publication_date"), resultSet.getString("title"),
						resultSet.getNString("brief"), resultSet.getString("content"), resultSet.getString("status"),
						resultSet.getInt("Users_id")));
			}
			return newsList;
		} catch (SQLException e) {
			throw new DAOException("Error");
		}
	}
}
