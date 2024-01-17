package by.news.management.controller.impl;

import java.io.IOException;

import by.news.management.bean.News;
import by.news.management.controller.Command;
import by.news.management.service.NewsService;
import by.news.management.service.ServiceException;
import by.news.management.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditNewsCommand implements Command {
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			News news = new News(request.getParameter("title"), request.getParameter("brief"),
					request.getParameter("content"), request.getParameter("status"),
					Integer.parseInt(request.getParameter("userId")), Integer.parseInt(request.getParameter("id")));
			newsService.editNews(news,Integer.parseInt(request.getSession(true).getAttribute("userId").toString()));
			String id = request.getParameter("id");
			String url = "Controller?command=show_news_view&id=" + id;

			response.sendRedirect(url);
		} catch (ServiceException e) {
			response.sendRedirect("Controller?command=show_error");
			e.printStackTrace();
		}
	}
}
