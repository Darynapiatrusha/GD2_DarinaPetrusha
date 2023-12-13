package by.news.management.dao;

import java.sql.Date;
import java.util.List;

import by.news.management.bean.News;
import by.news.management.dao.exceptions.DAOException;

public interface NewsDao {
	boolean addNews(News news) throws DAOException;
	boolean editNews(News news) throws DAOException;
	boolean deleteNews(int id) throws DAOException;
	News getByTitle(String title) throws DAOException;
	News getByDate(Date date) throws DAOException;
	News getById(int id) throws DAOException;
	List<News> getListOfNews(int startId, int endId) throws DAOException;
}
