package edu.kh.Achieve.member.model.service;

import static edu.kh.Achieve.common.JDBCTemplate.close;
import static edu.kh.Achieve.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import edu.kh.Achieve.member.model.dao.NoteDAO;
import edu.kh.Achieve.member.model.vo.Note;

public class NoteService {
	
	private NoteDAO dao = new NoteDAO();
	



	/**
	 * 알림 가져오기 Service
	 * @param memberNo
	 * @return
	 */
	public List<Note> noteList(int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		List<Note> nList = dao.NoteList(conn,memberNo);
		
		close(conn);
		
		return nList;
	}

	
	
	
	
	


}
