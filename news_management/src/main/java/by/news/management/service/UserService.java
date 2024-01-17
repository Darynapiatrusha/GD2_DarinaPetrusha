package by.news.management.service;

import by.news.management.bean.User;

public interface UserService {
	public void registation(User user) throws ServiceException;
	public User signIn(String login,String password) throws ServiceException;

}
