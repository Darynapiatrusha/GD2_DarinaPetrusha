package by.news.management.service;

import by.news.management.dao.DAOException;
import by.news.management.bean.User;

public interface UserService {
	public void Registation(User user) throws ServiceException, ClassNotFoundException;
	public boolean SignIn(String login,String password) throws ServiceException, ClassNotFoundException, DAOException;
	public void SignOut(String login) throws ServiceException;

}
