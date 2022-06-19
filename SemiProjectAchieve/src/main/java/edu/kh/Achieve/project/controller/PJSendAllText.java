package edu.kh.Achieve.project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.Achieve.project.model.service.ProjectService;

@WebServlet("/project/sendAllText")
public class PJSendAllText extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
		
		int projectNo = Integer.parseInt(req.getParameter("projectNo"));
		String boardContent = "<a href=\"board/main?type=1&projectNo=" + projectNo + "&cp=1\">"  + req.getParameter("boardContent") + "</a>"; 
		int loginMemberNo = Integer.parseInt(req.getParameter("loginMemberNo"));

		ProjectService service = new ProjectService();
		
		int result = service.insertNotice(boardContent, projectNo, loginMemberNo);
		
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
