package by.news.management.controller.impl;

import java.io.IOException;

import by.news.management.controller.Command;
import by.news.management.service.NewsService;
import by.news.management.service.ServiceException;
import by.news.management.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteNewsCommand implements Command {
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			newsService.deleteNews(id);

			String url = "Controller?command=show_news_list";
			response.sendRedirect(url);
			
		} catch (ServiceException e) {
			response.sendRedirect("Controller?command=show_error");
			e.printStackTrace();
		}
	}
}
