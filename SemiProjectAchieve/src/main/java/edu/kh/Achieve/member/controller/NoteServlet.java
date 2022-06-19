package edu.kh.Achieve.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import edu.kh.Achieve.member.model.service.NoteService;
import edu.kh.Achieve.member.model.vo.Member;
import edu.kh.Achieve.member.model.vo.Note;

@WebServlet("/note")
public class NoteServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			
			//1. 세션에서 로그인번호 가져오는 부분
			HttpSession session = req.getSession();
		
			//2. 세션에서 로그인번호 가져오는 부분
			Member loginMember = (Member)session.getAttribute("loginMember");
			
			NoteService service = new NoteService();			
			
			int memberNo = loginMember.getMemberNo();
			
			List<Note> nList = service.noteList(memberNo);
			
			//new Gson().toJson(nList, resp.getWriter());
			
			req.setAttribute("nList", nList);
			String path = "/WEB-INF/views/member/note.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
			
		
		}catch (Exception e) {
			e.printStackTrace();
			
			
			//ajax error 속성 활용을 위해 강제 에러 전달
			resp.setStatus(500); //(서버에러)
			
			resp.getWriter().print(e.getMessage());
		}
		
	}
}
