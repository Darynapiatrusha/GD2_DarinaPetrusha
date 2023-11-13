package by.management.news.service;

import by.management.news.bean.News;

public interface NewsService {
	public void addNews(News news) throws ServiceException;
	public void editNews(News news) throws ServiceException;
}
