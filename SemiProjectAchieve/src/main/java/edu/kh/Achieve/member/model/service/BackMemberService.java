package edu.kh.Achieve.member.model.service;

import static edu.kh.Achieve.common.JDBCTemplate.commit;
import static edu.kh.Achieve.common.JDBCTemplate.getConnection;
import static edu.kh.Achieve.common.JDBCTemplate.rollback;

import java.sql.Connection;

import edu.kh.Achieve.member.model.dao.BackMemberDAO;

public class BackMemberService {
	
	private BackMemberDAO dao = new BackMemberDAO();

	public int backMember(String backMemberNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.backMember(conn,backMemberNo);
		
		if(result > 0) commit(conn);
		else 		   rollback(conn);

		
		return result;
	}
	
	

}
