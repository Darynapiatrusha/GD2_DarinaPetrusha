package by.management.news.dao;

import by.management.news.bean.User;

public interface UserDao {
	public void signUp(User user) throws DaoExeption;
	public void logIn(String login,String password) throws DaoExeption;

}
