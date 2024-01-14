package by.news.management.controller.impl;

import java.io.IOException;

import by.news.management.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ShowAuthPageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).setAttribute("url", request.getRequestURI());
		request.getSession(true).setAttribute("queryString", request.getQueryString());
		request.getRequestDispatcher("WEB-INF/jsp/authorization.jsp").forward(request, response);
	}
}
