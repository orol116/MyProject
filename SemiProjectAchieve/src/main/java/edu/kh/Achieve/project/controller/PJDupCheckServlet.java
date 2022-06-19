package edu.kh.Achieve.project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.Achieve.project.model.service.ProjectService;

@WebServlet("/project/PJDupCheck")
public class PJDupCheckServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String projectName = req.getParameter("projectName");
		
		try {
			
			ProjectService service = new ProjectService();
			
			int result2 = service.PJDupCheck(projectName);
			
			resp.getWriter().print(result2);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
