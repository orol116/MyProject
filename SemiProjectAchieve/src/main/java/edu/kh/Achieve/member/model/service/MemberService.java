package edu.kh.Achieve.member.model.service;

import static edu.kh.Achieve.common.JDBCTemplate.close;
import static edu.kh.Achieve.common.JDBCTemplate.commit;
import static edu.kh.Achieve.common.JDBCTemplate.getConnection;
import static edu.kh.Achieve.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import edu.kh.Achieve.member.model.dao.MemberDAO;
import edu.kh.Achieve.member.model.vo.Member;
import edu.kh.Achieve.project.model.vo.Project;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();
	
	
	/**
	 * 로그인 Service
	 * @param mem
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(Member mem) throws Exception {
		
		Connection conn = getConnection();
		Member loginMember = dao.login(conn, mem);
		close(conn);
		return loginMember;
	}
	

	/**
	 * 회원 정보 수정
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Member mem) throws Exception{
		
		Connection conn = getConnection();
		int result = dao.updateMember(conn, mem);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);

		return result;
	}


	/**
	 * 비밀번호 변경 Service
	 * @param currentPw
	 * @param newPw
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int changePw(String currentPw, String newPw, int memberNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.changePw(conn, currentPw, newPw, memberNo);
		if(result > 0) commit(conn);
		else rollback(conn);
		
		
		close(conn);
		
		return result;
	}


	/** 회원가입 Service
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member mem) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.signUp(conn, mem);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
		
	/**
	 * 회원 탈퇴 Service
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int secession(int memberNo) throws Exception {
		Connection conn = getConnection();
		int result = dao.secession(conn, memberNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);	
		return result;
	}

	/** 이메일 중복검사 Service
	 * 
	 * @param memberEmail
	 * @return
	 * @throws Exception
	 */
	public int emailDupCheck(String memberEmail) throws Exception{
		
		Connection conn = getConnection(); // DBCP에서 만들어둔 커넥션 얻어오기
		
		int result = dao.emailDupCheck(conn, memberEmail);
		
		close(conn);
		
		return result;
	}
	
	

	/** 닉네임 중복 체크
	 * 
	 * @param memberNickname
	 * @return
	 */
	public int nicknameDupCheck(String memberNickname) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.nicknameDupCheck(conn,memberNickname);
		
		close(conn);
		
		return result;
	}


	/** 멤버 리스트 서비스
	 * 
	 * @return list
	 */
	public List<Member> selectAll(int projectNo) throws Exception{
		
		Connection conn = getConnection();
		
		List<Member> list  = dao.selectAll(conn, projectNo);
		
		close(conn);
		
		return list;
	}
	
	public List<Member> selectAllManager(int projectNo) throws Exception{
		
		Connection conn = getConnection();
		
		List<Member> list  = dao.selectAllManager(conn, projectNo);
		
		close(conn);
		
		return list;
	}


	/**
	 * 프로필 이미지 변경 Service
	 * @param memberNo
	 * @param profileImage
	 * @return result
	 * @throws Exception
	 */
	public int updateProfileImage(int memberNo, String profileImage) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updateProfileImage(conn, memberNo, profileImage);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}



	/**
	 * 아이디 찾기 아이디 조회
	 * @param memberName
	 * @param memberBirthday
	 * @return idList
	 * @throws Exception
	 */
	public List<Member> findIdAll(String memberName, String memberBirthday) throws Exception{
		Connection conn = getConnection();
		List<Member> idList = dao.findIdAll(conn, memberName, memberBirthday);
		close(conn);	
		
		return idList;
	}
	

	public int selectAllCount(int projectNo) throws Exception{
		Connection conn = getConnection();
		
		int count  = dao.selectAllCount(conn, projectNo);
		
		close(conn);
		
		return count;

	}
	public int selectAllCountManager(int projectNo) throws Exception{
		Connection conn = getConnection();
		
		int count  = dao.selectAllCountManager(conn, projectNo);
		
		close(conn);
		
		return count;

	}


	/**
	 * 비밀번호 재설정을 위한 아이디, 이름, 생년월일 일치 확인 Service
	 * @param memberEmail
	 * @param memberName
	 * @param memberBirthday
	 * @return result
	 * @throws Exception
	 */
	public int checkPw(String memberEmail, String memberName, String memberBirthday) throws Exception {
		Connection conn = getConnection();
		int result = dao.checkPw(conn, memberEmail, memberName, memberBirthday);
		close(conn);
		return result;
	}
	
	
	/** 내가 참여중인 프로젝트 조회 Service
	 * @param loginMember
	 * @throws Exception
	 * @return projectList
	 */
	public List<Project> selectMyJoinProjectService(Member loginMember) throws Exception {

		Connection conn = getConnection();
		
		List<Project> projectList = dao.selectMyJoinProjectService(conn, loginMember);
		
		close(conn);	
		
		return projectList;
		
	}


	/**
	 * 인증 번호를 db에 삽입하는 Service
	 * @param memberEmail
	 * @param cNumber
	 * @return certiResult
	 * @throws Exception
	 */
	public int insertCertification(String memberEmail, String cNumber) throws Exception {
		
		Connection conn = getConnection();
				
		// 1) 세션을 통해 전달받은 이메일과 일치하는 값이 있으면 수정(UPDATE)
		int certiResult = dao.updateCertification(conn, memberEmail, cNumber);
		
		// 2) 일치하는 이메일이 없는 경우 -> 처음으로 인증 번호를 발급 받은 것으로 삽입(
		if(certiResult == 0) {
			certiResult = dao.insertCertification(conn, memberEmail, cNumber);
			
		}
		
		if(certiResult > 0) commit(conn);
		else rollback(conn);
		
		close(conn);		
		
		return certiResult;
	}


	/**
	 * 인증번호 일치 여부 확인 및 비밀번호 재설정
	 * @param memberEmail
	 * @param certiChar
	 * @param memberPw
	 * @return result
	 * @throws Exception
	 */
	public int checkCerti(String memberEmail, String certiChar, String memberPw) throws Exception {
		
		Connection conn = getConnection();
		int result = dao.checkCerti(conn, memberEmail, certiChar);
		
		if(result > 0) {
			// 인증번호 일치
			result = dao.resetPw(conn, memberPw, memberEmail); // 1 : 재설정 성공 / 0 : 실패
		} 
		
		if(result > 0) commit(conn);
		else rollback(conn);

		close(conn);
		return result;
	}


	public int managerSelect(int projectNo) throws Exception{
		
		Connection conn = getConnection();
		
		int managerNo  = dao.managerSelect(conn, projectNo);
		
		close(conn);
		
		return managerNo;
	}

	
	
	
	
	


}
