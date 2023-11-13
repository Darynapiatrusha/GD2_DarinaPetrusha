package by.management.news.service;

import by.management.news.bean.User;

public interface UserService {
	public void signUp(User user) throws ServiceException;
	public void logIn(String login,String password) throws ServiceException;
	public void logOut(String login) throws ServiceException;

}
