package by.news.management.dao;

import java.sql.Date;
import java.util.List;

import by.news.management.bean.News;
import by.news.management.dao.exceptions.DAOException;

public interface NewsDao {
	int addNews(News news) throws DAOException;
	void editNews(News news, int userId) throws DAOException;
	void deleteNews(int id) throws DAOException;
	News getByTitle(String title) throws DAOException;
	News getByDate(Date date) throws DAOException;
	News getById(int id) throws DAOException;
	List<News> getListOfNews(int page) throws DAOException;
	List<Integer> getListOfPages()throws DAOException;
}
