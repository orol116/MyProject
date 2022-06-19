package edu.kh.Achieve.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.Achieve.board.model.service.BoardService;

@WebServlet("/board/ApproveBtn")
public class PassAccountServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		int memberNo = Integer.parseInt(req.getParameter("memberNo"));
		int projectNo = Integer.parseInt(req.getParameter("projectNo"));
		
		
		
		
		try {
			BoardService service = new BoardService();
			
			int result = service.passAccount(memberNo, projectNo);
			
			resp.getWriter().print(result);
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}
	

}
