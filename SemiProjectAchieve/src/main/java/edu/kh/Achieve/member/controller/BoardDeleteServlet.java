package edu.kh.Achieve.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import edu.kh.Achieve.member.model.service.CheckBoardService;

@WebServlet("/member/delete")
public class BoardDeleteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			HttpSession session = req.getSession();
			// 쿼리스트링으로 타입 얻어옴
			
			// delete?memNo&type=1&deleteNo=1,2,3
			
			int type =  Integer.parseInt(req.getParameter("type"));
			int memNo = ((int)session.getAttribute("memberNo"));
			String deleteNo = req.getParameter("deleteNo");			
			

			int result = 0;
			if(type == 1) {
				 result = new CheckBoardService().deleteBoard(deleteNo);
				
			}else {
				result = new CheckBoardService().deleteReply(deleteNo);
				
			}
			
			
			String path = null;
			String message = null;
			
			if(result > 0) { // 성공
				
				message = "선택하신 글이 삭제 되었습니다.";
				path = "List?memNo="+ memNo +"&type=" + type ; //해당 작성글 목록 1페이지로 이동
				// /List/delete/memNo=?type=1 
				
			}else { // 실패
				message = "선택하신 글 삭제에 실패했습니다.";
				path = req.getHeader("referer"); // 이전 요청 페이지 주소 == 상세페이지 == referer
			}

			session.setAttribute("message", message);
			
			resp.sendRedirect(path);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
		
}
