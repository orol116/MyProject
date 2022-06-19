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


import edu.kh.Achieve.member.model.vo.CheckBoard;
import edu.kh.Achieve.member.model.vo.CheckPagination;

public class CheckReplyDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public CheckReplyDAO() {
		
		try {
			prop = new Properties();
			
			String filePath = CheckReplyDAO.class.getResource("/edu/kh/Achieve/sql/checkReply-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
