package by.news.management.listener;

import by.news.management.dao.connection.ConnectionPool;
import by.news.management.dao.connection.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ConnectionPoolListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			ConnectionPool.getInstance().initPoolData();
		} catch (ConnectionPoolException e) {
			throw new RuntimeException(e);
		}
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ConnectionPool.getInstance().dispose();
	}
}
