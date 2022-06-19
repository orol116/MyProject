package edu.kh.Achieve.board.model.service;

import static edu.kh.Achieve.common.JDBCTemplate.close;
import static edu.kh.Achieve.common.JDBCTemplate.commit;
import static edu.kh.Achieve.common.JDBCTemplate.getConnection;
import static edu.kh.Achieve.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.Achieve.board.model.dao.BoardDAO;
import edu.kh.Achieve.board.model.vo.Board;
import edu.kh.Achieve.board.model.vo.BoardAttachment;
import edu.kh.Achieve.board.model.vo.BoardDetail;
import edu.kh.Achieve.board.model.vo.Pagination;
import edu.kh.Achieve.common.Util;

public class BoardService {
	
	private BoardDAO dao = new BoardDAO();

	/** 게시글 메인 페이지 Service
	 * @param type
	 * @param cp
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> selectBoardMain(int type, int cp, int projectNo) throws Exception {
	
		Connection conn = getConnection();
		
		String boardName = dao.selectBoardName(conn, type, projectNo);
		
		int listCount = 0;
		
		if (type == 1) {
			listCount = dao.getNewListCount(conn, projectNo);
		} else {
			listCount = dao.getListCount(conn, type, projectNo);
		}
		
		
		Pagination pagination = new Pagination(cp, listCount);
		
		List<Board> boardList = null;
		
		if (type == 1) { // 최신글 조회
			boardList = dao.selectBoardMainList(conn, pagination, projectNo);
		} else {
			boardList = dao.selectBoardMain(conn, pagination, type, projectNo);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardName", boardName);
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		close(conn);
		
		return map;
	}

	/** 게시글 검색 목록 조회 Service
	 * @param type
	 * @param cp
	 * @param key
	 * @param query
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> searchBoardList(int type, int cp, String key, String query, int projectNo) throws Exception {

		Connection conn = getConnection();
		
		String boardName = dao.selectBoardName(conn, type, projectNo);
		
		String condition = null;
		
		switch (key) {
		case "t" : condition = " AND BOARD_TITLE LIKE '%"+query+"%' "; break;
		case "c" : condition = " AND BOARD_CONTENT LIKE '%"+query+"%' "; break;
		case "tc" : condition = " AND (BOARD_TITLE LIKE '%"+query+"%' OR BOARD_CONTENT LIKE '%"+query+"%') "; break;
		case "w" : condition = " AND MEMBER_NICK LIKE '%"+query+"%' "; break;
		}
		
		int listCount = dao.searchListCount(conn, type, condition, projectNo);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		List<Board> boardList = dao.searchBoardList(conn, pagination, type, condition, projectNo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardName", boardName);
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		close(conn);
		
		return map;
		
		
	}

	/** 게시글 등록 Service
	 * @param detail
	 * @param imageList
	 * @param boardCode
	 * @return boardNo
	 * @throws Exception
	 */
	public int insertBoard(BoardDetail detail, List<BoardAttachment> boardAttachmentList, int boardCode, int projectNo) throws Exception {
		
		Connection conn = getConnection();

		int boardNo = dao.nextBoardNo(conn);

		detail.setBoardNo(boardNo);
		
		detail.setBoardTitle(Util.XSSHandling(detail.getBoardTitle()));
		
		int result = dao.insertBoard(conn, detail, boardCode, projectNo);
		
		if (result > 0) {
			for (BoardAttachment image : boardAttachmentList) { 
				image.setBoardNo(boardNo);
				
				result = dao.insertBoardAttachment(conn, image, projectNo);
				
				if (result == 0) {
					break;
				}
			}
			
		}
		
		if (result > 0) commit(conn);
		else {
			rollback(conn);
			boardNo = 0;
		}
		
		close(conn);

		return boardNo;
	}


	/** 게시판 종류 조회 Service
	 * @param type
	 * @return boardTypeList
	 * @throws Exception
	 */
	public List<Board> selectboardTypeList(int projectNo) throws Exception {

		Connection conn = getConnection();
		
		List<Board> boardTypeList = dao.selectboardType(conn, projectNo);
	
		close(conn);
		
		return boardTypeList;
	}
	
	
	public int deleteBoard(int boardNo, int projectNo) throws Exception{

		Connection conn = getConnection();
		
		int result = dao.deleteBoard(boardNo, projectNo, conn);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 게시글 상세조회 service
	 * 
	 * @param boardNo
	 * @return detail
	 * @throws Exception
	 */
	public BoardDetail selectBoardDetail(int boardNo, int projectNo) throws Exception{
		
		Connection conn = getConnection();
		
		// 1) 게시글 (BOARD 테이블) 내용만 조회
		BoardDetail detail = dao.selectBoarDetail(conn, boardNo, projectNo);
		
		if(detail != null) { // 게시글 상세 조회 결과가 있을 경우에 
			
		// 2) 게시글에 첨부된 이미지(BOARD_IMG 테이블) 조회
			List<BoardAttachment> attachmentList = dao.selectAttachmentList(conn, boardNo);
			
			// -> 조회된 imageList 를 BoardDetail 객체에 세팅
			
			detail.setAttachmentList(attachmentList);
		}
		
		close(conn);
		
		return detail;
	}

	/** 프로젝트 이름 조회 Service
	 * @param projectNo
	 * @return projectName
	 * @throws Exception
	 */

	public String selectProjectName(int projectNo) throws Exception {
		
		Connection conn = getConnection();
		
		String projectName = dao.selectProjectName(conn, projectNo);
		
		close(conn);
		
		return projectName;
	}

	public int updateBoard(BoardDetail detail, List<BoardAttachment> boardAttachmentList, String deleteList) throws Exception{
		
		Connection conn	 = getConnection();
		
		
		// 1. 게시글 부분(제목, 내용, 마지막 수정일)수정
		// 1) XSS 방지 처리(제목, 내용)
		
		detail.setBoardTitle(Util.XSSHandling(detail.getBoardTitle()));
		
//		detail.setBoardContent(Util.XSSHandling(detail.getBoardContent()));
//		
//		// 2) 개행문자 처리(내용)
//		detail.setBoardContent(Util.newLineHandling(detail.getBoardContent()));
		
		// 3) DAO 호출
		int result = dao.updateBoard(conn, detail);
		
		if(result>0) { // 게시글 수정 성공 시
			
			// 2. 이미지 부분 수정 ( 기존 -> 변경, 없다가 추가되는 경우 총 2가지 경우 처리)
			for(BoardAttachment attach:boardAttachmentList) {
				
				attach.setBoardNo(detail.getBoardNo()); // 게시글 번호 세팅
				
				// 이미지 1개씩 수정
				result = dao.updateBoardAttachment(conn, attach);
				
				// result ==1 : 수정 성공
				// result ==0 : 수정 실패 -> 기존에 없다가 새로 추가된 이미지일 경우
				// 							-> insert 진행해야된다. 
				
				if(result == 0) {
					result = dao.insertBoardAttachment(conn, attach);
				}
			} // 향상된 for문 끝 
			
			
			// 3. 이미지 삭제
			// deleteList ("1,2,3" 이런 모양, 없으면 빈문자열("") )
			
			if(!deleteList.equals("")) { // 삭제된 이미지 레벨이 기록되어있을 때만 삭제
				
				result =dao.deleteBoardAttachment(conn, deleteList, detail.getBoardNo(), detail.getProjectNo());
				
			}
		}// 게시글 수정 성공 시 if 끝
		
		if(result>0) commit(conn);
		else rollback(conn);

		close(conn);
		
		return result;
	}
	
	
	/** 프로젝트 소개 조회 Service
	 * @param projectNo
	 * @return projectName
	 * @throws Exception
	 */

	public String selectProjectIntro(int projectNo) throws Exception {
		
		Connection conn = getConnection();
		String projectIntro = dao.selectProjectIntro(conn, projectNo);
		close(conn);
		
		return projectIntro;

	}
	
	

	public int passAccount(int memberNo, int projectNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.passAccount(conn,memberNo, projectNo);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		
		
		return result;
	}

	/** 프로젝트 관리자(생성자) 회원 번호 조회 Service
	 * @param projectNo
	 * @return projectAdminNo
	 * @throws Exception
	 */
	public int selectProjectAdminNo(int projectNo) throws Exception {

		Connection conn = getConnection();
		
		int projectAdminNo = dao.selectProjectAdminNo(conn, projectNo);
		
		close(conn);
		
		return projectAdminNo;

	}
	

}
