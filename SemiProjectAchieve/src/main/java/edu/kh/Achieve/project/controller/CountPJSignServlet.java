package edu.kh.Achieve.project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.Achieve.project.model.service.ProjectService;

@WebServlet("/project/CountPJSign")
public class CountPJSignServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int projectNo = Integer.parseInt(req.getParameter("projectNo"));
		
		
		
		try {
			
			ProjectService service = new ProjectService();
			
			int count = service.selectPJ(projectNo);
			
			resp.getWriter().print(count); ;
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	

}
