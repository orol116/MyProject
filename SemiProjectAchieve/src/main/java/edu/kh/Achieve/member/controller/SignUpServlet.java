package edu.kh.Achieve.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.Achieve.member.model.service.MemberService;
import edu.kh.Achieve.member.model.vo.Member;

@WebServlet("/member/signUp")
public class SignUpServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/member/signUp.jsp";
		
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memberEmail = req.getParameter("memberEmail");
		String memberPw = req.getParameter("memberPw");
		String memberTel = req.getParameter("memberTel");
		String memberName = req.getParameter("memberName");
		String memberNickname = req.getParameter("memberNickname");
		
		String[] birth = req.getParameterValues("memberBirth");
		
		String memberBirth = null;
		
		memberBirth = String.join("-",birth);
		
		Member mem = new Member();
		
		mem.setMemberEmail(memberEmail);
		mem.setMemberPw(memberPw);
		mem.setMemberTel(memberTel);
		mem.setMemberName(memberName);
		mem.setMemberNickname(memberNickname);
		mem.setMemberBirthday(memberBirth);
		
		try {
			MemberService service = new MemberService();
			
			int result = service.signUp(mem);
			
			HttpSession session = req.getSession();
			
			if(result>0) {
				session.setAttribute("message", "회원가입 성공");
			}else {
				session.setAttribute("message", "회원가입 실패");
			}
			
			resp.sendRedirect(req.getContextPath());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
