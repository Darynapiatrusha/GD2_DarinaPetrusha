package by.news.management.dao;

import java.util.List;

import by.news.management.bean.User;

public interface UserDao {
	boolean registration(User user) throws DAOException;
	boolean signIn(String login, String password) throws DAOException,NoSuchUserException;
	List<User> getListOfUsers(int quantity) throws DAOException;
	User getByLogin(String login) throws DAOException;
	User updateUser(User user) throws DAOException;
	boolean deleteUser(User user) throws DAOException;
}