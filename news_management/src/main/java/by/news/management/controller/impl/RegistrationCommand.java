package by.news.management.controller.impl;

import java.io.IOException;

import by.news.management.bean.Roles;
import by.news.management.bean.Status;
import by.news.management.bean.User;
import by.news.management.controller.Command;
import by.news.management.service.ServiceException;
import by.news.management.service.ServiceProvider;
import by.news.management.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RegistrationCommand implements Command {
	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String login = request.getParameter("login");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Roles role = Roles.USER;
		Status status = Status.ACTIVE;
		
		User user = new User(name, surname, login, email, password, role, status);
		
		try {
			userService.registation(user);
			HttpSession session = request.getSession(true);
			session.setAttribute("userName", user.getName());
			session.setAttribute("userId", user.getId());
			session.setAttribute("userRole", user.getRoles());

			String mes = "RegistrationOK";
			response.sendRedirect("Controller?command=show_news_list&page=1&info_message=" + mes);

		} catch (ServiceException e) {
			response.sendRedirect("Controller?command=error_registration&error_message='errorWithRegistration'");
			e.printStackTrace();
		}
	}
}
