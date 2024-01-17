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
import jakarta.servlet.http.HttpSession;

public class SignInCommand implements Command {
	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			User user = userService.signIn(request.getParameter("login"), request.getParameter("password"));
			HttpSession session = request.getSession(true);
			session.setAttribute("userName", user.getName());
			session.setAttribute("userId", user.getId());
			session.setAttribute("userRole", user.getRoles());

			response.sendRedirect("Controller?command=show_news_list&page=1");

		} catch (ServiceException e) {
			response.sendRedirect("Controller?command=error_auth&error_message='errorWithAuthentification'");
		}
	}
}
