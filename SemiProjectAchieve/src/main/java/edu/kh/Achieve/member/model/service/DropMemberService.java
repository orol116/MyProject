package edu.kh.Achieve.member.model.service;

import static edu.kh.Achieve.common.JDBCTemplate.*;

import java.sql.Connection;


import edu.kh.Achieve.member.model.dao.DropMemberDAO;

public class DropMemberService {
	
	private DropMemberDAO dao = new DropMemberDAO();

	public int dropMember(String dropMemberNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.dropMember(conn,dropMemberNo);
		
		if(result > 0) commit(conn);
		else 		   rollback(conn);

		
		return result;
	}
	
	

}
