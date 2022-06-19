package edu.kh.Achieve.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.Achieve.project.model.dao.ProjectDAO;

import static edu.kh.Achieve.common.JDBCTemplate.*;

public class DropMemberDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public DropMemberDAO() {
		try {
			prop = new Properties();
			
			String filePath = ProjectDAO.class.getResource("/edu/kh/Achieve/sql/project-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

	public int dropMember(Connection conn, String dropMemberNo) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("dropMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dropMemberNo);
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
			
		}
		
		
		return result;
	}

}
