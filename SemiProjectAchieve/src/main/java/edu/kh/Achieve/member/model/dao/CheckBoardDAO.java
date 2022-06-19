package edu.kh.Achieve.member.model.dao;

import static edu.kh.Achieve.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.Achieve.board.model.vo.Board;
import edu.kh.Achieve.board.model.vo.Pagination;
import edu.kh.Achieve.board.model.vo.Reply;
import edu.kh.Achieve.member.model.vo.CheckBoard;
import edu.kh.Achieve.member.model.vo.CheckPagination;
import edu.kh.Achieve.member.model.vo.CheckReply;
import edu.kh.Achieve.member.model.vo.Member;
import edu.kh.Achieve.project.model.vo.Project;

public class CheckBoardDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public CheckBoardDAO() {
		
		try {
			prop = new Properties();
			String filePath = CheckBoardDAO.class.getResource("/edu/kh/Achieve/sql/checkBoard-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 작성글 수 조회 DAO
	 * @param conn
	 * @param memNo 
	 * @return listCount
	 * @throws Exception
	 */
	public int getBoardListCount(Connection conn , int type, int memNo) throws Exception{
		
		int listBoardCount = 0;
		
		try{
			String sql = prop.getProperty("getBoardListCount");
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				listBoardCount = rs.getInt(1);
			}
		}finally{
			close(rs);
			close(pstmt);
		}
		return listBoardCount;
		
	}
	
	/** 작성글 목록 조회 DAO
	 * @param conn
	 * @param type
	 * @return listName
	 * @throws Exception
	 */
	public String selectListName(Connection conn, int type, int memNo) throws Exception{
		String listName = null;
		
		try{
			String sql = prop.getProperty("selectListName");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listName = rs.getString(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return listName;
	}



	/** 회원 닉네임, 프로필 이미지 조회 DAO
	 * @param memNick
	 * @param pImage
	 * @return memberList
	 * @throws Exception
	 */
	public List<Member> selectMemberDetail(Connection conn, String memNick, String pImage, int memNo) throws Exception {

		List<Member> memList = new ArrayList<Member>();
		
		try {
			
			
			String sql = prop.getProperty("selectMemList");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Member mem = new Member();
				
				mem.setMemberNickname(rs.getString("MEMBER_NICK"));
				mem.setProfileImage(rs.getString("MEMBER_PROFILE"));
	
				memList.add(mem);
			}
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return memList;
	}


	/** 작성글에서 일정한 범위의 목록 조회 DAO
	 * @param conn
	 * @param pagination
	 * @param type
	 * @param memNo 
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Connection conn, CheckPagination pagination, int type, int memNo) throws Exception {
		
		List<Board> boardList = new ArrayList<Board>();
		
		try {
			String sql = prop.getProperty("selectBoardList");
			
			// BETWEEN 구문에 들어갈 범위 계산
			int start =  (pagination.getCurrentPage() - 1) * pagination.getLimit() +1;
			int end = start + pagination.getLimit()-1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setProjectNo(rs.getInt("PROJECT_NO"));
				
				boardList.add(board);
			
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;
	}

	

	/** 작성글 삭제 DAO
	 * @param conn 
	 * @param deleteNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(Connection conn, String deleteNo) throws Exception {

		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteBoard") + deleteNo + " )";
			pstmt = conn.prepareStatement(sql);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
//-------------------------------------------------------------------------
	
	/** 작성 댓글 개수 조회 DAO
	 * @param conn
	 * @param type
	 * @param memNo
	 * @return listCount
	 * @throws Exception
	 */
	public int getReplyListCount(Connection conn, int type, int memNo) throws Exception{
	
		int listReplyCount = 0;
		
		try{
			String sql = prop.getProperty("getReplyListCount");
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				listReplyCount = rs.getInt(1);
			}
		}finally{
			close(rs);
			close(pstmt);
		}
		return listReplyCount;
	}

	/** 작성 댓글 목록 
	 * @param conn
	 * @param pagination
	 * @param type
	 * @param memNo
	 * @return replyList
	 * @throws Exception
	 */
	public List<Reply> selectReplyList(Connection conn, CheckPagination pagination, int type, int memNo) throws Exception{
		List<Reply> replyList = new ArrayList<Reply>();
		
		try {
			String sql = prop.getProperty("selectReplyList");
			
			// BETWEEN 구문에 들어갈 범위 계산
			int start =  (pagination.getCurrentPage() - 1) * pagination.getLimit() +1;
			int end = start + pagination.getLimit()-1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Reply reply = new Reply();
				
				reply.setReplyNo(rs.getInt("REPLY_NO"));
				reply.setReplyContent(rs.getString("REPLY_CONTENT"));
				reply.setReplyDate(rs.getString("REPLY_DT"));
				reply.setBoardTitle(rs.getString("BOARD_TITLE"));
				reply.setProjectNo(rs.getInt("PROJECT_NO"));
				reply.setBoardNo(rs.getInt("BOARD_NO"));
			
				replyList.add(reply);
			
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return replyList;
	}

	/** 댓글 삭제 DAO
	 * @param conn
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteReply(Connection conn, String deleteNo) throws Exception{

		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteReply") + deleteNo + " )";
			pstmt = conn.prepareStatement(sql);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	// ------------------------------------------------------
	
	/** 가입된 프로젝트 개수 조회 DAO
	 * @param conn
	 * @param type
	 * @param memNo
	 * @return map
	 * @throws Exception
	 */
    public int getProjectListCount(Connection conn, int type, int memNo) throws Exception{
		int listProjectCount = 0;
		
		try{
			String sql = prop.getProperty("getProjectListCount");
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listProjectCount = rs.getInt(1);
			}
		}finally{
			close(rs);
			close(pstmt);
		}
		return listProjectCount;
		
	}

	/** 가입된 프로젝트 목록 조회 DAO
	 * @param conn
	 * @param pagination
	 * @param type
	 * @param memNo
	 * @return projectList
	 * @throws Exception
	 */
	public List<Project> selectProjectList(Connection conn, CheckPagination pagination, int type, int memNo) throws Exception{
		
		List<Project> projectList = new ArrayList<Project>();
		
		try {
			String sql = prop.getProperty("selectProjectList");
			
			// BETWEEN 구문에 들어갈 범위 계산
			int start =  (pagination.getCurrentPage() - 1) * pagination.getLimit() +1;
			int end = start + pagination.getLimit()-1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Project project = new Project();
				
				project.setProjectNo(rs.getInt("PROJECT_NO"));
				project.setProjectNM(rs.getString("PROJECT_NM"));
//				project.setBoardNo(rs.getInt("BOARD_NO"));
				
				projectList.add(project);
			
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return projectList;
	}


}
