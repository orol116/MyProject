package edu.kh.Achieve.board.model.service;

import static edu.kh.Achieve.common.JDBCTemplate.close;
import static edu.kh.Achieve.common.JDBCTemplate.commit;
import static edu.kh.Achieve.common.JDBCTemplate.getConnection;
import static edu.kh.Achieve.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import edu.kh.Achieve.board.model.dao.ReplyDAO;
import edu.kh.Achieve.board.model.vo.Reply;
import edu.kh.Achieve.common.Util;



public class ReplyService {
	
	private ReplyDAO dao = new ReplyDAO();

	/** 댓글 목록 조회 Service
	 * @param boardNo
	 * @return rList
	 * @throws Exception
	 */
	public List<Reply> selectReplyList(int boardNo) throws Exception{
		
		Connection conn = getConnection(); // DBCP에서 커넥션 얻어오기
		
		// 댓글 목록 조회 SQL 수행 후 결과 반환 받기
		List<Reply> rList = dao.selectReplyList(conn, boardNo);
		
		close(conn);
		
		return rList;
	}

	public int insertReply(Reply reply) throws Exception{
		
		Connection conn = getConnection();
		
		reply.setReplyContent(Util.XSSHandling(reply.getReplyContent()));
		
		
		// 개행문자 변경 처리
		// textarea에 줄바꿈 문자 입력시 \n , \r, \r\n, \n\r 중 하나로 입력이 된다(브라우저, os따라 다름)
		// 이 문자들을 html 에서 줄바꿈으로 인식할 수 있도록 "<br>" 태그로 변경
		// reply.getReplyContent().replaceAll("정규 표현식", "바꿀 문자열");

		
		// reply.setReplyContent(reply.getReplyContent().replaceAll("(\n|\r|\r\n|\n\r)", "<br>"));
		
		reply.setReplyContent(Util.newLineHandling(reply.getReplyContent()));
		
		int result = dao.insertReply(conn, reply);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int deleteReply(int replyNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.deleteReply(conn, replyNo);
		
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	
	public int updateReply(int replyNo, String replyContent) throws Exception{
		
		Connection conn = getConnection();
		
		replyContent = Util.XSSHandling(replyContent);
		
		replyContent= Util.newLineHandling(replyContent);
		
		int result = dao.updateReply(replyNo, replyContent, conn);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	

}
