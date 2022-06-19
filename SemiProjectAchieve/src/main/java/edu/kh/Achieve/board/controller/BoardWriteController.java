package edu.kh.Achieve.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import edu.kh.Achieve.board.model.service.BoardService;
import edu.kh.Achieve.board.model.vo.Board;
import edu.kh.Achieve.board.model.vo.BoardAttachment;
import edu.kh.Achieve.board.model.vo.BoardDetail;
import edu.kh.Achieve.common.MyRenamePolicy;
import edu.kh.Achieve.member.model.vo.Member;

@WebServlet("/board/write")
public class BoardWriteController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {

			int projectNo = Integer.parseInt(req.getParameter("projectNo"));
			String mode = req.getParameter("mode"); 

			if(mode.equals("update")) {
				
				int boardNo = Integer.parseInt(req.getParameter("no")); 
				
				
				BoardDetail detail = new BoardService().selectBoardDetail(boardNo, projectNo);

				detail.setBoardContent(detail.getBoardContent().replaceAll("<br>", "\n"));
				
				req.setAttribute("detail", detail); 
				
			}
			
			String path = "/WEB-INF/views/board/board-write.jsp";
			
			List<Board> boardTypeList = new BoardService().selectboardTypeList(projectNo);
			req.setAttribute("boardTypeList", boardTypeList);
			
			
			req.getRequestDispatcher(path).forward(req, resp);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {

			int maxSize = 1024 * 1024 * 500; // 500MB 제한 (수정 가능)
			
			HttpSession session = req.getSession();
			String root = session.getServletContext().getRealPath("/");
			String folderPath = "/resources/boardAttachment/";
			String filePath = root + folderPath;
			
			String encoding = "UTF-8";
	
			MultipartRequest mpReq = new MultipartRequest(req, filePath, maxSize, encoding, new MyRenamePolicy());
			
			Enumeration<String> files = mpReq.getFileNames();
			
			List<BoardAttachment> boardAttachmentList = new ArrayList<BoardAttachment>();
			
			int projectNo = Integer.parseInt(mpReq.getParameter("projectNo"));
			
			while (files.hasMoreElements()) {
				String name = files.nextElement();
				
				String rename = mpReq.getFilesystemName(name);
				String original = mpReq.getOriginalFileName(name);
				
				if (rename != null) {
					
					BoardAttachment attachment = new BoardAttachment();
					
					attachment.setAttachmentOriginal(original);
					attachment.setAttachmentReName(folderPath + rename);
					attachment.setAttachmentLevel(Integer.parseInt(name));
					attachment.setProjectNo(projectNo);
					
					boardAttachmentList.add(attachment);
				}
			}
			
			String boardTitle = mpReq.getParameter("boardTitle");
			String boardContent = mpReq.getParameter("boardContent");
			int boardCode = Integer.parseInt(mpReq.getParameter("board-type"));
			
			
			session.setAttribute("projectNo", projectNo);
			
			Member loginMember = (Member)session.getAttribute("loginMember");
			int memberNo = loginMember.getMemberNo();
			
			BoardDetail detail = new BoardDetail();
			detail.setBoardTitle(boardTitle);
			detail.setBoardContent(boardContent);
			detail.setMemberNo(memberNo);
			
			BoardService service = new BoardService();
	
			String mode = mpReq.getParameter("mode");
			
			if (mode.equals("insert")) { // 삽입
				
				int boardNo = service.insertBoard(detail, boardAttachmentList, boardCode, projectNo);
				String path = null;
				
				if (boardNo > 0) {
					session.setAttribute("message", "게시글이 등록되었습니다.");
					path = "detail?no=" + boardNo + "&type=" + boardCode + "&projectNo=" + projectNo;
					
				} else {
					session.setAttribute("message", "게시글 등록 실패");
					path = "write?mode=" + mode + "&type=" + boardCode + "&projectNo=" + projectNo;
				}
				
				resp.sendRedirect(path);
				
			}
			if(mode.equals("update")) { // 수정
				// 앞선 코드는 동일(업로드된 이미지 저장, imageList 생성, 제목/내용 파라미터 전부 동일)
				
				//  + update일 때 추가된 내용
				//  1. 어떤 게시글 수정할 것인지-> 파라미터 no
				//  2. 나중에 목록으로 버튼 만들 때 사용할 현재 페이지 -> 파라미터 cp
				//  3. 이미지 중 x버튼을 눌러서 삭제할 이미지 레벨 목록 -> 파라미터 deleteList
				int boardNo = Integer.parseInt(mpReq.getParameter("no"));
				
				int cp = Integer.parseInt(mpReq.getParameter("cp"));
				
				String deleteList = mpReq.getParameter("deleteList"); // 1,2,3
				
				// 게시글 수정 서비스 호출 후 결과 반환 받기
				detail.setBoardNo(boardNo);
				detail.setProjectNo(projectNo);
				
				// detail, imageList , deleteList
				int result = service.updateBoard(detail,boardAttachmentList, deleteList);
				String path = null;
				String message = null;
				
				if(result>0) { // 성공
					// detail?no==10000&type=1&cp=20 이런 모양
					path = "detail?no="+boardNo+"&projectNo="+projectNo+"&type="+boardCode+"&cp="+cp; // 상세 조회 페이지 요청 주소 
					
					message = "게시글이 수정되었습니다.";
					
				}else { // 실패
					
					// 다시 수정화면으로 이동
					// 상세조회 -> 수정화면 -> 수정 -> (성공) 상세조회
					//								-> (실패) 수정화면
					
					path=req.getHeader("referer");
					// referer : http 요청 흔적. 요청 바로 이전 페이지 주소가 담겨있다. 네이버에서 카카오로 요청해서 이동하면 referer에 네이버 주소가 담겨있다!!
					
					message="게시글 수정 실패";
					
				}
				
				session.setAttribute("message", message); // 리다이렉트를 위해 세션에 올려둔다.
				resp.sendRedirect(path);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
}
