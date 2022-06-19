package edu.kh.Achieve.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.Achieve.member.model.service.MemberService;

@WebServlet("/findPwResult")
public class FindPwResultServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/views/member/findPwResult.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 가져오기
		String certiChar = req.getParameter("certiChar");
		String memberPw = req.getParameter("memberPw");
		
		try {
			
			MemberService service = new MemberService();
			
			// 세션에 전달해둔 이메일 주소 가져오기 위한 세션 불러오기
			HttpSession session = req.getSession();
			String memberEmail = (String)session.getAttribute("memberEmail");
			
			int result = service.checkCerti(memberEmail, certiChar, memberPw);
			
			if(result > 0) {
				// 비밀번호 변경 성공 -> 메인페이지로 
				session.setAttribute("message", "비밀번호 재설정 성공");
				resp.sendRedirect(req.getContextPath());
				
			} else {
				// 비밀번호 변경 실패 -> 해당 페이지
				session.setAttribute("message", "비밀번호 재설정 실패");
				resp.sendRedirect(req.getContextPath() + "/findPwResult");
			}

			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}
