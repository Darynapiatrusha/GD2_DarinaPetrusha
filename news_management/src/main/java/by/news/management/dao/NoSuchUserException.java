package by.news.management.dao;

public class NoSuchUserException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoSuchUserException() {
		super();
	}

	public NoSuchUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchUserException(String message) {
		super(message);
	}
}
