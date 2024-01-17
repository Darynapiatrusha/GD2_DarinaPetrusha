package by.news.management.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CommandProvider provider = CommandProvider.getInstance();

	public Controller() {
		super();
	}

	private void doProccess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			String name = request.getParameter("command");
			Command command = provider.getCommand(name);
			command.execute(request, response);
		} catch (IllegalArgumentException e) {
			request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProccess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProccess(request, response);
	}
}
