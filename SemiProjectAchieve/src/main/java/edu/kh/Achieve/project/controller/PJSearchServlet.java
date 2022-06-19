package edu.kh.Achieve.project.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import edu.kh.Achieve.member.model.vo.Member;
import edu.kh.Achieve.project.model.service.ProjectService;
import edu.kh.Achieve.project.model.vo.Project;

@WebServlet("/project/PJ/PJSearch/list" ) 
public class PJSearchServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			
		try {
				
				int cp = 1;
				// 페이지네이션의 번호 선택 시
				// 쿼리스트링에 cp가 있음 --> cp = 쿼리스트링의 cp 값
				if( req.getParameter("cp") != null  ) { // 쿼리스트링에 "cp"가 존재한다면
					cp = Integer.parseInt( req.getParameter("cp") );
				}
						
				// 페이지네이션 객체, 프로젝트 리스트를 한 번에 반환하는 Service 호출
				ProjectService service = new ProjectService();				
				Map<String, Object> map = null;
				
				// 회원 번호
				HttpSession session = req.getSession();
				Member loginMember = (Member)(session.getAttribute("loginMember"));
				int memberNo = loginMember.getMemberNo();
				
				// 회원 번호를 가지고 프로젝트 전체 목록 조회 (랜덤으로 몇 개만??? 나중에...) 		
				if( req.getParameter("key") == null ) { // 전체 목록 조회
					
					map = service.searchAll(cp, memberNo);
					
				}else { // 검색 목록 조회
					String key = req.getParameter("key");
					String query = req.getParameter("query");
					
					map = service.searchProjectList(cp, key, query, memberNo);
				}

				
				// request 범위로 map을 세팅
				req.setAttribute("map", map);
				
				String path = "/WEB-INF/views/project/PJSearch.jsp";
				
				RequestDispatcher dispatcher = req.getRequestDispatcher(path);
				
				
				dispatcher.forward(req, resp);

		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	


}