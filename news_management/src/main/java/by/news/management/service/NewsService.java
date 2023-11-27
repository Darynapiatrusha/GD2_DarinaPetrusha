package by.news.management.service;

import by.news.management.bean.News;

public interface NewsService {
	public void addNews(News news) throws ServiceException;
	public void editNews(News news) throws ServiceException;
}
