package by.news.management.dao;

import by.news.management.dao.impl.SQLNewsDao;
import by.news.management.dao.impl.SQLUserDao;

public final class DaoProvider {
	private static final DaoProvider instance = new DaoProvider();

	private final UserDao userDaoImpl = new SQLUserDao();
	private final NewsDao newsDaoImpl = new SQLNewsDao();

	private DaoProvider() {
	}

	public static final DaoProvider getInstance() {
		return instance;
	}

	public final UserDao getUserDao() {
		return userDaoImpl;
	}

	public final NewsDao getNewsDao() {
		return newsDaoImpl;
	}
}
