package by.news.management.dao.connection;

import java.util.ResourceBundle;

public final class DBResourceManager {
	private final static DBResourceManager instance = new DBResourceManager();

	private ResourceBundle jdbcProperties = ResourceBundle.getBundle("db");

	public static DBResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return jdbcProperties.getString(key);
	}
}
