package edu.kh.Achieve.project.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.kh.Achieve.project.model.service.ProjectService;
import edu.kh.Achieve.project.model.vo.ProjectSign;

@WebServlet("/project/selectPJSign")
public class SelectPJSignServlet extends HttpServlet{
	
	

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int projectNo = Integer.parseInt(req.getParameter("projectNo"));

		
		try {
			
			ProjectService service = new ProjectService();
			
			List<ProjectSign> list = service.selectPJSign(projectNo);
			
			new Gson().toJson( list, resp.getWriter() );
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	

}
