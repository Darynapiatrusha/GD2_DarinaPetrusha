package by.management.news.dao;

import by.management.news.bean.News;

public interface NewsDao {
	public void addNews(News news) throws DaoExeption;
	public void editNews() throws DaoExeption;
	public void deleteNews() throws DaoExeption;
}
