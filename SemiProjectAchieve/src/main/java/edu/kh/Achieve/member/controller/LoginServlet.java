package edu.kh.Achieve.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.Achieve.member.model.service.MemberService;
import edu.kh.Achieve.member.model.vo.Member;
import edu.kh.Achieve.project.model.vo.Project;

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String inputEmail = req.getParameter("inputEmail");
		String inputPw = req.getParameter("inputPw");
		
		// 파라미터를 VO에 세팅
		Member mem = new Member();
		mem.setMemberEmail(inputEmail);
		mem.setMemberPw(inputPw);
		
		try {
			MemberService service = new MemberService();
			
			// 입력된 이메일과 비밀번호가 일치하는 회원 조회
			Member loginMember = service.login(mem);

			// Email, Pw 일치하는 회원 정보를 Session에 저장
			
			// 1. session 객체 얻어오기
			HttpSession session = req.getSession();
			
			if(loginMember != null) {
				// 로그인 한 회원의 회원 정보를 session에 세팅
				// session에 loginMember라는 객체로 담겨있음!! 
				session.setAttribute("loginMember", loginMember);
				
				// 특정 시간 후 요청 없을 시 세션 만료
				session.setMaxInactiveInterval(3600);
				
				// 아이디 저장
				Cookie c = new Cookie("saveId", inputEmail);
				
				// 아이디 저장이 체크된 경우
				if(req.getParameter("saveId") != null) {
					c.setMaxAge(60*60*24*30);
				} else {
					c.setMaxAge(0);
				}
				
				c.setPath(req.getContextPath());
				
				resp.addCookie(c);
				
				List<Project> projectList = service.selectMyJoinProjectService(loginMember);
				session.setAttribute("projectList", projectList);
				
				
				
			} else {
				session.setAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
			}
			
			resp.sendRedirect(req.getContextPath());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}