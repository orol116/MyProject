package edu.kh.Achieve.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.Achieve.member.model.service.BackMemberService;

@WebServlet("/member/backMember")
public class BackMemberServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String backMemberNo = req.getParameter("memberNo");
		
		try {
			
			BackMemberService service = new BackMemberService();
			
			int result = service.backMember(backMemberNo);
			
			resp.getWriter().print(result);
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	}

}
