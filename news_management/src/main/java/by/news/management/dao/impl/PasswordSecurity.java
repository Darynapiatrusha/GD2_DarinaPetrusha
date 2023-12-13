package by.news.management.dao.impl;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordSecurity {
	
	public static String hashPassword(String password) {
		String hashedPassword = null;
		hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

		return hashedPassword;
	}

	public static boolean passwordCheck(String password, String hashedPassword) {
		boolean check = BCrypt.checkpw(password, hashedPassword);

		return check;
	}
}
