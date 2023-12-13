package by.news.management.service.impl;

import by.news.management.dao.DaoProvider;
import by.news.management.dao.UserDao;
import by.news.management.dao.exceptions.DAOException;
import by.news.management.dao.exceptions.UserNotFoundException;
import by.news.management.bean.User;
import by.news.management.service.ServiceException;
import by.news.management.service.UserService;

public class UserServiceImpl implements UserService {
	private final UserDao userDao = DaoProvider.getInstance().getUserDao();

	@Override
	public void registation(User user) throws ServiceException {
		try {

			userDao.registration(user);

		} catch (DAOException e) {
			throw new ServiceException("Error in process registration", e);
		}
	}

	@Override
	public User signIn(String login, String password) throws ServiceException {
		try {
			return userDao.signIn(login, password);
		} catch (DAOException | UserNotFoundException e) {
			throw new ServiceException("Error in process of signin", e);
		}
	}

	@Override
	public void signOut(String login) throws ServiceException {

	}

}
