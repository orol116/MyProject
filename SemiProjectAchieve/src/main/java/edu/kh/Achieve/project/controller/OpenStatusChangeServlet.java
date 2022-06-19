package edu.kh.Achieve.project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.Achieve.project.model.service.ProjectService;

@WebServlet("/project/openStatusChange")
public class OpenStatusChangeServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String openStatus = req.getParameter("openStatus");
		
		
		int projectNo = Integer.parseInt(req.getParameter("projectNo"));
		
		try {
			
			ProjectService service = new ProjectService();
			
			int result = service.changeStatus(openStatus, projectNo);
			
			resp.getWriter().print(result);
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		
	}

}
