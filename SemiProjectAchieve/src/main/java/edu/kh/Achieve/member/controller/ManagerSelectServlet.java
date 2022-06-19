package edu.kh.Achieve.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.Achieve.member.model.service.MemberService;

@WebServlet("/board/manager")	
public class ManagerSelectServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			int projectNo = Integer.parseInt(req.getParameter("projectNo"));
			
			
			MemberService service = new MemberService();
			
			int managerNo = service.managerSelect(projectNo);
			
			resp.getWriter().print(managerNo); 
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
