package edu.kh.Achieve.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.Achieve.member.model.service.DropMemberService;
import edu.kh.Achieve.member.model.service.MemberService;

@WebServlet("/member/dropMember")
public class DropMemberServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String path = "/WEB-INF/views/member/dropMember-list.jsp";
		
		req.getRequestDispatcher(path).forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String dropMemberNo = req.getParameter("memberNo");
		
		try {
			
			DropMemberService service = new DropMemberService();
			
			int result = service.dropMember(dropMemberNo);
			
			resp.getWriter().print(result);
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	}

}
