package by.news.management.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.news.management.bean.News;
import by.news.management.dao.DaoProvider;
import by.news.management.dao.NewsDao;
import by.news.management.dao.exceptions.DAOException;
import by.news.management.service.NewsService;
import by.news.management.service.ServiceException;

public class NewsServiceImpl implements NewsService {
	private final NewsDao NewsDao = DaoProvider.getInstance().getNewsDao();
	private final static Logger log = LogManager.getRootLogger();

	@Override
	public int addNews(News news) throws ServiceException {
		try {
			int id = NewsDao.addNews(news);
			return id;
		} catch (DAOException e) {
			log.error("Error of add news process", e);
			throw new ServiceException("News was not add to database", e);
		}
	}

	@Override
	public void editNews(News news, int userId) throws ServiceException {
		try {
			NewsDao.editNews(news, userId);
		} catch (DAOException e) {
			log.error("Error of edit news process", e);
			throw new ServiceException("News was not updated in database", e);
		}
	}

	@Override
	public void deleteNews(int id) throws ServiceException {
		try {
			NewsDao.deleteNews(id);
		} catch (DAOException e) {
			log.error("Error of delete news process");
			throw new ServiceException("News was not deleted in database", e);
		}
	}

	@Override
	public List<News> getListOfNews(int page) throws ServiceException {
		try {
			return NewsDao.getListOfNews(page);
		} catch (DAOException e) {
			log.error("Error of get list of news process");
			throw new ServiceException("News was not selecting from database", e);
		}
	}

	@Override
	public News getById(int id) throws ServiceException {
		try {
			return NewsDao.getById(id);
		} catch (DAOException e) {
			log.error("Error of get news by id process");
			throw new ServiceException("News with this id was not found in database", e);
		}
	}

	@Override
	public List<Integer> getListOfPages() throws ServiceException {
		try {
			return NewsDao.getListOfPages();
		} catch (DAOException e) {
			log.error("Error of getListOfPages process");
			throw new ServiceException("List of pages was not found in database", e);
		}
	}
}
