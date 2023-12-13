package by.news.management.controller;

import java.io.IOException;

import by.news.management.dao.connection.ConnectionPool;
import by.news.management.dao.connection.ConnectionPoolException;
import jakarta.servlet.ServletConfig;
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

	@Override
	public void init(ServletConfig config) throws ServletException {
			try {
				ConnectionPool.getInstance().initPoolData();
			} catch (ConnectionPoolException e) {
				throw new RuntimeException(e);
			}
	}

	private void doProccess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String name = request.getParameter("command");
		Command command = provider.getCommand(name);
		command.execute(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProccess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProccess(request, response);
	}
	@Override
    public void destroy() {
        ConnectionPool.getInstance().dispose();
    }
}
