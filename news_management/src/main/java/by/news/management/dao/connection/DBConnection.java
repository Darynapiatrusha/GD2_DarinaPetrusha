package by.news.management.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Failed to load jdbc.Driver", e);
		}
	}

	public static Connection getDBConnection() throws SQLException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/news_management?useUnicode=true&serverTimezone=UTC&useSSL=false",
					"root", "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");
		} catch (SQLException e) {
			throw new SQLException("Failed to establish DataBase connections",e);
		}
		return connection;
	}
}