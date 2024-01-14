package by.news.management.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SecurityFilterAdmin implements Filter {

	public void init(FilterConfig config) throws ServletException {
		System.out.println("init");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain next)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
//		if (request.getParameter("command").equals("show_edit_new") && !request.getSession().getAttribute("userRoles").equals("ADMIN")) {
//			System.out.println((request.getParameter("command")));
//			System.out.println("done");
//			request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
//		}else {
//			request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
//		}
		next.doFilter(request, response);
	}

	public void destroy() {
		System.out.println("destroy");
	}

}
