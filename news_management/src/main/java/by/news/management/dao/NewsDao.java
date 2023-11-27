package by.news.management.dao;

import java.sql.Date;
import java.util.List;

import by.news.management.bean.News;
import by.news.management.bean.User;

public interface NewsDao {
	boolean addNews(News news,User user) throws DAOException;
	boolean editNews(News news) throws DAOException;
	boolean deleteNews(News news) throws DAOException;
	News getByTitle(String title) throws DAOException;
	News getByDate(Date date) throws DAOException;
	List<News> getListOfNews(int quantity) throws DAOException;
}
