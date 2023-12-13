package by.news.management.dao;

import java.util.List;

import by.news.management.bean.User;
import by.news.management.dao.exceptions.DAOException;
import by.news.management.dao.exceptions.UserNotFoundException;

public interface UserDao {
	void registration(User user) throws DAOException;
	User signIn(String login, String password) throws DAOException,UserNotFoundException;
	List<User> getListOfUsers(int quantity) throws DAOException;
	User getByLogin(String login) throws DAOException;
	User updateUser(User user) throws DAOException;
	boolean deleteUser(User user) throws DAOException;
	boolean blockUser(User user) throws DAOException;
}