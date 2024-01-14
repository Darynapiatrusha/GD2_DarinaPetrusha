package by.news.management.controller.impl;

import java.io.IOException;

import by.news.management.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChangeLocalCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).setAttribute("local", request.getParameter("local"));
		String queryString = request.getSession(true).getAttribute("queryString").toString();
		String url =  request.getSession(true).getAttribute("url").toString();
		String urlAddress = new StringBuilder(url).append("?").append(queryString).toString();
		response.sendRedirect(urlAddress);
	} 

}
