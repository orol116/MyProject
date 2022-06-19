package edu.kh.Achieve.project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.Achieve.member.model.vo.Member;
import edu.kh.Achieve.project.model.service.ProjectService;
import edu.kh.Achieve.project.model.vo.ProjectSign;

@WebServlet("/project/AccountMember")
public class AccountMemberServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int memberNo = Integer.parseInt(req.getParameter("memberNo"));
		int projectNo = Integer.parseInt(req.getParameter("projectNo"));
		
		
		
		try {
			
			ProjectService service = new ProjectService();
			
			int result = service.accountMember(memberNo, projectNo);
			
			resp.getWriter().print(result);
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}
	

}
