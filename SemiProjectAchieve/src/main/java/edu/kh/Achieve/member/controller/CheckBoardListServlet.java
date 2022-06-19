package edu.kh.Achieve.member.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.Achieve.member.model.service.CheckBoardService;
import edu.kh.Achieve.member.model.vo.Member;

@WebServlet("/member/List")
public class CheckBoardListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			//1.  현재페이지 cp
			int cp = 1;
			if(req.getParameter("cp") != null) { // 쿼리스트링에 "cp"가 존재 한다면
				cp = Integer.parseInt(req.getParameter("cp"));
			}
			
			// 로그인 회원 번호
			HttpSession session = req.getSession();
			Member loginMember = (Member)session.getAttribute("loginMember");
			
			int memNo = loginMember.getMemberNo();
			

			String memNick = loginMember.getMemberNickname();
			String pImage = loginMember.getProfileImage();
			
			// /board/list?type=1 (작성글)
			// /board/list?type=2 (댓글)
			int type = Integer.parseInt(req.getParameter("type"));
	
			//  페이지네이션 객체, 게시글 리스트를 한 번에 반환하는 Service 호출
			CheckBoardService service = new CheckBoardService();
			
			Map<String, Object> map = null;
			
			
			if(type == 1) {

				map = service.selectBoardList(cp, type, memNo, memNick, pImage);
			
			}else if(type == 2) {
				
				map = service.selectReplyList(cp, type, memNo, memNick, pImage);
			
			}else if(type == 3) {
				
				map = service.selectProjectList(cp, type, memNo, memNick, pImage);
			
			}

			req.setAttribute("map", map);
			req.setAttribute("memNo", memNo);
			
			String path = "/WEB-INF/views/member/myPage-boardList.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
