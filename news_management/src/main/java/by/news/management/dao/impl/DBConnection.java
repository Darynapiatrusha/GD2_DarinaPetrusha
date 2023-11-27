package by.news.management.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
	public static Connection getDBConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/news_management?useUnicode=true&serverTimezone=UTC&useSSL=false",
					"root", "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");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
