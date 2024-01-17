package by.news.management.controller.impl;

import java.io.IOException;
import java.util.List;

import by.news.management.bean.News;
import by.news.management.controller.Command;
import by.news.management.service.NewsService;
import by.news.management.service.ServiceException;
import by.news.management.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ShowNewsListCommand implements Command {
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		try {
			List<News> news = newsService.getListOfNews(page);
			List<Integer> numberOfPages = newsService.getListOfPages();
			request.setAttribute("listOfNews", news);
			request.setAttribute("numberOfPages", numberOfPages);
			request.getSession(true).setAttribute("url", request.getRequestURI());
			request.getSession(true).setAttribute("queryString", request.getQueryString());
			request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
		} catch (ServiceException e) {
			response.sendRedirect("Controller?command=show_error");
			e.printStackTrace();
		}
	}
}
