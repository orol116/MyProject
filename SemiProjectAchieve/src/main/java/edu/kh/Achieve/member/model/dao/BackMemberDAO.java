package edu.kh.Achieve.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.Achieve.project.model.dao.ProjectDAO;

import static edu.kh.Achieve.common.JDBCTemplate.*;

public class BackMemberDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public BackMemberDAO() {
		try {
			prop = new Properties();
			
			String filePath = ProjectDAO.class.getResource("/edu/kh/Achieve/sql/project-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

	public int backMember(Connection conn, String backMemberNo) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("backMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, backMemberNo);
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
			
		}
		
		return result;
	}

}
