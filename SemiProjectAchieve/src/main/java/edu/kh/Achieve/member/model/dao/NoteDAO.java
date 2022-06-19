package edu.kh.Achieve.member.model.dao;

import static edu.kh.Achieve.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.Achieve.member.model.vo.Note;

public class NoteDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public NoteDAO() {
		try {
			prop = new Properties();
			String filePath = MemberDAO.class.getResource("/edu/kh/Achieve/sql/note-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * 알림 조회 DAO
	 * @param conn
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public List<Note> NoteList(Connection conn, int memberNo) throws Exception{
		
		List<Note> nList = new ArrayList<Note>();
		
		try {
			
			String sql = prop.getProperty("NoteList");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Note note = new Note();
				
				note.setSender(rs.getString("MEMBER_NICK"));
				note.setNoteContent(rs.getString("NOTE_CONTENT"));
				
				note.setReceiverNo(memberNo);
				
				nList.add(note);
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return nList;
	}

}
