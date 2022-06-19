package edu.kh.Achieve.board.controller;

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

import edu.kh.Achieve.board.model.service.BoardService;
import edu.kh.Achieve.board.model.vo.Board;
import edu.kh.Achieve.member.model.vo.Member;
import edu.kh.Achieve.project.model.service.ProjectService;


@WebServlet("/board/main")
public class BoardMainController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
		
			int type = Integer.parseInt(req.getParameter("type"));
			int projectNo = Integer.parseInt(req.getParameter("projectNo"));
			int cp = 1;
			
			if (req.getParameter("cp") != null) cp = Integer.parseInt(req.getParameter("cp"));
			
			BoardService service = new BoardService();

			String projectName = service.selectProjectName(projectNo);
			req.setAttribute("projectName", projectName);
			
			String projectIntro = service.selectProjectIntro(projectNo);
			req.setAttribute("projectIntro", projectIntro);
			
			List<Board> boardTypeList = service.selectboardTypeList(projectNo);
			req.setAttribute("boardTypeList", boardTypeList);
			
			int projectAdminNo = service.selectProjectAdminNo(projectNo);
			req.setAttribute("projectAdminNo", projectAdminNo);
			
			// 프로젝트 리스트 가져오기
			HttpSession session = req.getSession();
			Member loginMember = (Member)(session.getAttribute("loginMember"));
			int memberNo = loginMember.getMemberNo();
			req.setAttribute("memberNo", memberNo);

			Map<String, Object> map = null;
			
			if (req.getParameter("key") == null) {
				map = service.selectBoardMain(type, cp, projectNo);
			} else {
				String key = req.getParameter("key");
				String query = req.getParameter("query");
				
				map = service.searchBoardList(type, cp, key, query, projectNo);
			}
			
			req.setAttribute("map", map);
			
			String path = "/WEB-INF/views/board/board-main.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
