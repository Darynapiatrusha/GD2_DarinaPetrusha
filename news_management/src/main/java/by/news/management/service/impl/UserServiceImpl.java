package by.news.management.service.impl;

import by.news.management.dao.DAOException;
import by.news.management.dao.DaoProvider;
import by.news.management.dao.NoSuchUserException;
import by.news.management.dao.UserDao;
import by.news.management.bean.User;
import by.news.management.service.ServiceException;
import by.news.management.service.UserService;

public class UserServiceImpl implements UserService {
	private final UserDao userDao = DaoProvider.getInstance().getUserDao();

	@Override
	public void Registation(User user) throws ServiceException, ClassNotFoundException {
		try {
			userDao.registration(user);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean SignIn(String login, String password) throws ServiceException, ClassNotFoundException {
		boolean checkedPassword = true;
		boolean check = true;
		try {
			checkedPassword = userDao.signIn(login, password);
			if (checkedPassword != true) {
				check = true;
			} else {
				check = false;
			}
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (NoSuchUserException e) {
			e.printStackTrace();
		}
		return check;

	}

	@Override
	public void SignOut(String login) throws ServiceException {

	}

}
