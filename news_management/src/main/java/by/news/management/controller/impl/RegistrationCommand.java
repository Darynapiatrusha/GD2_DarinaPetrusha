package by.news.management.controller.impl;

import java.io.IOException;

import by.news.management.bean.User;
import by.news.management.controller.Command;
import by.news.management.service.ServiceException;
import by.news.management.service.ServiceProvider;
import by.news.management.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {
		User user = new User(request.getParameter("name"), request.getParameter("surname"),
				request.getParameter("login"), request.getParameter("e-mail"), request.getParameter("password"),request.getParameter("status"));
		try {
			userService.Registation(user);
			request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();

		}
	}
}
