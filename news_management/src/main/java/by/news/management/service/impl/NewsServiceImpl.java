package by.news.management.service.impl;

import java.util.List;

import by.news.management.bean.News;
import by.news.management.dao.DaoProvider;
import by.news.management.dao.NewsDao;
import by.news.management.dao.exceptions.DAOException;
import by.news.management.service.NewsService;
import by.news.management.service.ServiceException;

public class NewsServiceImpl implements NewsService {
	private final NewsDao NewsDao = DaoProvider.getInstance().getNewsDao();

	@Override
	public int addNews(News news) throws ServiceException {
		try {
			int id = NewsDao.addNews(news);
			return id;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void editNews(News news, int userId) throws ServiceException {
		try {
			NewsDao.editNews(news, userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteNews(int id) throws ServiceException {
		try {
			NewsDao.deleteNews(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> getListOfNews(int page) throws ServiceException {
		try {
			return NewsDao.getListOfNews(page);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public News getById(int id) throws ServiceException {
		try {
			return NewsDao.getById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Integer> getListOfPages() throws ServiceException {
		try {
			return NewsDao.getListOfPages();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
