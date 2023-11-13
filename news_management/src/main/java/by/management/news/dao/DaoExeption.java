package by.management.news.dao;

public class DaoExeption extends Exception{
	private static final long serialVersionUID = 1L;
	public DaoExeption() {
		super();
	}

	public DaoExeption(String message) {
		super(message);
	}

	public DaoExeption(Exception e) {
		super(e);
	}

	public DaoExeption(String message, Exception e) {
		super(message, e);
	}
}
