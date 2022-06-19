package edu.kh.Achieve.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.kh.Achieve.member.model.service.MemberService;
import edu.kh.Achieve.member.model.vo.Member;

@WebServlet("/findId")
public class findIdServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = "/WEB-INF/views/member/findIdPw.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파라미터 얻어오기
		String memberName = req.getParameter("memberName");
		String memberBirthday = req.getParameter("memberBirthday");

		
		try {
			MemberService service = new MemberService();
			List<Member> idList = service.findIdAll(memberName, memberBirthday);
			
			new Gson().toJson(idList, resp.getWriter());
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
