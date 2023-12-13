package by.news.management.service;

import by.news.management.bean.User;
import by.news.management.dao.exceptions.DAOException;

public interface UserService {
	public void registation(User user) throws ServiceException;
	public User signIn(String login,String password) throws ServiceException;
	public void signOut(String login) throws ServiceException;

}
