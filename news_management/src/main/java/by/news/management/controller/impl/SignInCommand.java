package by.news.management.controller.impl;

import java.io.IOException;

import by.news.management.dao.DAOException;
import by.news.management.controller.Command;
import by.news.management.service.ServiceException;
import by.news.management.service.ServiceProvider;
import by.news.management.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignInCommand implements Command {
	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {

		try {
			if (userService.SignIn(request.getParameter("login"), request.getParameter("password")) == true) {
				request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("wrong-authorization.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | ServiceException | DAOException e) {
			e.printStackTrace();
		}
	}
}
