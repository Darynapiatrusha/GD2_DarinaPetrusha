package by.news.management.filter;

import java.io.IOException;

import by.news.management.bean.Roles;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SecurityFilterAdmin implements Filter {

	public void init(FilterConfig config) throws ServletException {
		System.out.println("init");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain next)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession(true);

		String commandNameFromURL = request.getParameter("command");

		boolean isLoggedIn = session != null;
		boolean notLoggedIn = session.getAttribute("userRole") == null;
		boolean isManager = (session.getAttribute("userRole") == Roles.MANAGER);
		boolean isAdmin = (session.getAttribute("userRole") == Roles.ADMIN);
		boolean isUser = (session.getAttribute("userRole") == Roles.USER);
		boolean isSuperAdmin = (session.getAttribute("userRole") == Roles.SUPER_ADMIN);

		if (commandNameFromURL.equals("show_edit_news") && isUser | notLoggedIn) {
			response.sendRedirect("Controller?command=show_error");
		} else if (commandNameFromURL.equals("create_news") && isUser | notLoggedIn) {
			response.sendRedirect("Controller?command=show_error");
		} else if (commandNameFromURL.equals("delete_news") && isUser | isManager | notLoggedIn) {
			response.sendRedirect("Controller?command=show_error");
		} else if (commandNameFromURL.equals("save_news") && isUser | notLoggedIn) {
			response.sendRedirect("Controller?command=show_error");
		} else {
			next.doFilter(request, response);
		}
	}

	public void destroy() {
		System.out.println("destroy");
	}
}
