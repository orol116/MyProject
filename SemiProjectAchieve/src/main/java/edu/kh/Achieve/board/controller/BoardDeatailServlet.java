package edu.kh.Achieve.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.Achieve.board.model.service.BoardService;
import edu.kh.Achieve.board.model.service.ReplyService;
import edu.kh.Achieve.board.model.vo.BoardDetail;
import edu.kh.Achieve.board.model.vo.Reply;

@WebServlet("/board/detail")
public class BoardDeatailServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
//			파라미터 중 게시글 번호(no) 얻어오기
			int boardNo = Integer.parseInt(req.getParameter("no"));
//			Integer.parseInt(req.getParameter("projectNo"));
			int projectNo = Integer.parseInt(req.getParameter("projectNo"));
			
			BoardService  service = new BoardService();

			BoardDetail detail = service.selectBoardDetail(boardNo, projectNo);
			
			// 게시글 상세조회된 내용이 있을 경우 댓글 목록 조회
			if(detail != null) {
				List<Reply> rList = new ReplyService().selectReplyList(boardNo);
				req.setAttribute("rList", rList);
			}
			
			req.setAttribute("detail", detail);
			
			String path = "/WEB-INF/views/board/board-detail.jsp";
			
			RequestDispatcher dispathcer  = req.getRequestDispatcher(path);
			
			dispathcer.forward(req, resp);
			
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
