package edu.kh.Achieve.member.model.dao;

import static edu.kh.Achieve.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import edu.kh.Achieve.member.model.vo.Member;
import edu.kh.Achieve.project.model.vo.Project;

public class MemberDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public MemberDAO() {
		try {
			prop = new Properties();
			String filePath = MemberDAO.class.getResource("/edu/kh/Achieve/sql/member-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 로그인 DAO
	 * @param conn
	 * @param mem
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(Connection conn, Member mem) throws Exception{
		Member loginMember = null;
		
		try {
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getMemberEmail());
			pstmt.setString(2, mem.getMemberPw());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginMember = new Member();
				loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
				loginMember.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				loginMember.setMemberName(rs.getString("MEMBER_NM"));
				loginMember.setMemberNickname(rs.getString("MEMBER_NICK"));
				loginMember.setMemberBirthday(rs.getString("MEMBER_BIRTH"));
				loginMember.setMemberTel(rs.getString("MEMBER_TEL"));
				loginMember.setProfileImage(rs.getString("MEMBER_PROFILE"));
			}

		} finally {
			close(rs);
			close(pstmt);			
		}
		
		return loginMember;
	}
	

	/**
	 * 회원정보 수정 DAO
	 * @param conn
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Connection conn, Member mem) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateMember");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getMemberName());
			pstmt.setString(2, mem.getMemberNickname());
			pstmt.setString(3, mem.getMemberTel());
			pstmt.setInt(4, mem.getMemberNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}


	/**
	 * 비밀번호 변경 DAO
	 * @param conn
	 * @param currentPw
	 * @param newPw
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int changePw(Connection conn, String currentPw, String newPw, int memberNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("changePw");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPw);
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, currentPw);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);			
		}
		return result;
	}


	/** 회원가입 DAO
	 * @param conn
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member mem) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("signUp");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mem.getMemberEmail());
			pstmt.setString(2, mem.getMemberPw());
			pstmt.setString(3, mem.getMemberName());
			pstmt.setString(4, mem.getMemberNickname());
			pstmt.setString(5, mem.getMemberBirthday());
			pstmt.setString(6, mem.getMemberTel());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	/**
	 * 회원 탈퇴 DAO
	 * @param conn
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int secession(Connection conn, int memberNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("secession");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/**
	 * 프로필 이미지 변경 DAO
	 * @param conn
	 * @param memberNo
	 * @param profileImage
	 * @return result
	 * @throws Exception
	 */
	public int updateProfileImage(Connection conn, int memberNo, String profileImage) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateProfileImage");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, profileImage);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}
	

	/** 이메일 중복 체크
	 * @param conn
	 * @param memberEmail
	 * @return
	 * @throws Exception
	 */
	public int emailDupCheck(Connection conn, String memberEmail) throws Exception{
		
		int result = 0; // 결과 저장용 변수
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("emailDupCheck");
			
			// pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			// 위치홀더에 알맞은 값 세팅
			pstmt.setString(1, memberEmail);
			
			// SQL(SELECT) 수행 후 결과 반환 받기
			rs = pstmt.executeQuery();
			
			// rs.next() 로 조회결과를 확인
			if( rs.next() ) {
				result = rs.getInt(1); // 1번 컬럼 결과를 result에 대입
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
	
	/** 닉네임 중복 체크
	 * 
	 * @param conn
	 * @param memberNickname
	 * @return
	 * @throws Exception
	 */
	public int nicknameDupCheck(Connection conn, String memberNickname) throws Exception{
		
		int result =0;
		
		try {
			String sql = prop.getProperty("nicknameDupCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberNickname);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}


	/** 멤버 리스트 DAO
	 * @param conn
	 * @return list
	 * @throws Exception
	 */
	public List<Member> selectAll(Connection conn, int projectNo) throws Exception{
	
//		List<Member>  list = new ArrayList<Member>();
		
		List<Member> list = new ArrayList<Member>();
		
		try {
			String sql = prop.getProperty("selectAllMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, projectNo);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				Member mem = new Member();
				
				mem.setMemberNo( rs.getInt(1) );
				mem.setMemberNickname(rs.getString(2));
				mem.setProfileImage(rs.getString(3));
				mem.setSuspensionFlag(rs.getString(4));
				
				list.add(mem); // 리스트 추가
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return list;
	}
	
	public List<Member> selectAllManager(Connection conn, int projectNo) throws Exception{
		
//		List<Member>  list = new ArrayList<Member>();
		
		List<Member> list = new ArrayList<Member>();
		
		try {
			String sql = prop.getProperty("selectAll");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, projectNo);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				Member mem = new Member();
				
				mem.setMemberNo( rs.getInt(1) );
				mem.setMemberNickname(rs.getString(2));
				mem.setProfileImage(rs.getString(3));
				mem.setSuspensionFlag(rs.getString(4));
				
				list.add(mem); // 리스트 추가
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return list;
	}


	/**
	 * 아이디 찾기 아이디 조회
	 * @param conn
	 * @param memberName
	 * @param memberBirthday
	 * @return idList
	 * @throws Exception
	 */
	public List<Member> findIdAll(Connection conn, String memberName, String memberBirthday) throws Exception {
		List<Member> idList = new ArrayList<Member>();
		
		try {
			String sql = prop.getProperty("findIdList");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberBirthday);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				member.setMemberEmail(rs.getString(1));
				
				idList.add(member);
			}

		} finally {
			close(rs);
			close(pstmt);
		}
		return idList;
	}

		
	public int selectAllCount(Connection conn, int projectNo) throws Exception{
		
		int count = 0;
		
		try {
			String sql = prop.getProperty("selectAllCountMember");
			
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setInt(1, projectNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return count;

	}
	public int selectAllCountManager(Connection conn, int projectNo) throws Exception{
		
		int count = 0;
		
		try {
			String sql = prop.getProperty("selectAllCount");
			
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setInt(1, projectNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return count;

	}

	/** 내가 참여중인 프로젝트 조회 DAO
	 * @param conn
	 * @param loginMember
	 * @return projectList
	 * @throws Exception
	 */
	public List<Project> selectMyJoinProjectService(Connection conn, Member loginMember) throws Exception {
		
		List<Project> projectList = new ArrayList<Project>();

		try {
			
			String sql = prop.getProperty("selectMyJoinProject");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginMember.getMemberNo());
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				Project project = new Project();
				
				project.setProjectNo(rs.getInt(1));
				project.setProjectName(rs.getString(2));
				project.setProjectIntro(rs.getString(3));
				project.setOpenStatus(rs.getString(4));

				projectList.add(project);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return projectList;
		
	}


	/**
	 * 비밀번호 재설정을 위한 아이디, 이름, 생년월일 일치 확인 DAO
	 * @param conn
	 * @param memberEmail
	 * @param memberName
	 * @param memberBirthday
	 * @return result
	 * @throws Exception
	 */
	public int checkPw(Connection conn, String memberEmail, String memberName, String memberBirthday) throws Exception{
		int result = 0;
		try {
			String sql = prop.getProperty("checkPw");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			pstmt.setString(2, memberName);
			pstmt.setString(3, memberBirthday);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}

		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}


	/**
	 * 일치하는 메일 주소가 있는 경우 인증번호 update DAO
	 * @param conn
	 * @param memberEmail
	 * @param cNumber
	 * @return certiResult
	 * @throws Exception
	 */
	public int updateCertification(Connection conn, String memberEmail, String cNumber) throws Exception{

		int certiResult = 0;
		
		try {
			String sql = prop.getProperty("updateCertification");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cNumber);
			pstmt.setString(2, memberEmail);
			
			certiResult = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return certiResult;
	}


	/**
	 * 일치하는 메일 주소가 없는 경우 인증번호 insert DAO
	 * @param conn
	 * @param memberEmail
	 * @param cNumber
	 * @return
	 * @throws Exception
	 */
	public int insertCertification(Connection conn, String memberEmail, String cNumber) throws Exception {
		
		int certiResult = 0;
		
		try {
			String sql = prop.getProperty("insertCertification");
			
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, memberEmail);
	         pstmt.setString(2, cNumber);
	         
	         certiResult = pstmt.executeUpdate();

		}finally {
			
			close(pstmt);
		}
		
		return certiResult;
	}


	/**
	 * 인증번호 일치여부 확인 DAO
	 * @param conn
	 * @param memberEmail
	 * @param certiChar
	 * @return result
	 * @throws Exception
	 */
	public int checkCerti(Connection conn, String memberEmail, String certiChar) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("checkCerti");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			pstmt.setString(2, certiChar);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}


	/**
	 * 비밀번호 재설정 DAO
	 * @param conn
	 * @param memberPw
	 * @param memberEmail
	 * @return result
	 * @throws Exception
	 */
	public int resetPw(Connection conn, String memberPw, String memberEmail) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("resetPw");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberPw);
			pstmt.setString(2, memberEmail);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	public int managerSelect(Connection conn, int projectNo) throws Exception{
		
		int manager = 0;
		
		try {
			String sql = prop.getProperty("managerSelect");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, projectNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				manager = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return manager;
	}



	
	
	
	
}
