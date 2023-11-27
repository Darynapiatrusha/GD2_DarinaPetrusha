package by.news.management.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CommandProvider provider = CommandProvider.getInstance();

	public Controller() {
		super();
	}

	private void doProccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
		response.setContentType("text/html");
		String name = request.getParameter("command");
		Command command = provider.getCommand(name);
		command.execute(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doProccess(request, response);
		} catch (ClassNotFoundException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doProccess(request, response);
		} catch (ClassNotFoundException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
