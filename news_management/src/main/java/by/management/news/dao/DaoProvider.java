package by.management.news.dao;

import by.management.news.dao.impl.SQLNewsDao;
import by.management.news.dao.impl.SQLUserDao;

public final class DaoProvider {
	private static final DaoProvider instance = new DaoProvider();

	private final UserDao userDaoImpl = new SQLUserDao();
	private final NewsDao newsDaoImpl = new SQLNewsDao();

	private DaoProvider() {
	};

	public static DaoProvider getInstance() {
		return instance;
	}

	public UserDao getUserDao() {
		return userDaoImpl;
	}

	public NewsDao getNewsDao() {
		return newsDaoImpl;
	}
}
