package by.news.management.service;

import java.util.List;

import by.news.management.bean.News;

public interface NewsService {
	public int addNews(News news) throws ServiceException;
	public void editNews(News news, int userId) throws ServiceException;
	public void deleteNews(int id) throws ServiceException;
	List<News> getListOfNews(int page) throws ServiceException;
	News getById(int id) throws ServiceException;
	List<Integer> getListOfPages()throws ServiceException;
}
