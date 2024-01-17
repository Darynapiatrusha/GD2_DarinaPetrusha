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

public class ShowNewsViewCommand implements Command {
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			News newsItem = newsService.getById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("newsItem", newsItem);
			request.getSession(true).setAttribute("url", request.getRequestURI());
			request.getSession(true).setAttribute("queryString", request.getQueryString());
			request.getRequestDispatcher("WEB-INF/jsp/news.jsp").forward(request, response);
		} catch (ServiceException e) {
			response.sendRedirect("Controller?command=show_error");
			e.printStackTrace();
		}
	}
}
