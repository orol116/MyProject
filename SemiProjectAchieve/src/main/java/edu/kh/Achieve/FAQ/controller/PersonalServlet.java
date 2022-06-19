package edu.kh.Achieve.FAQ.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FAQ/personal/info")
public class PersonalServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	String path = "/WEB-INF/views/FAQ/personal-info.jsp";
		
		req.getRequestDispatcher(path).forward(req, resp);
	}
	

}
