package edu.kh.Achieve.project.model.service;

import static edu.kh.Achieve.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.kh.Achieve.board.model.vo.Pagination;
import edu.kh.Achieve.member.model.dao.DropMemberDAO;
import edu.kh.Achieve.project.model.dao.ProjectDAO;
import edu.kh.Achieve.project.model.vo.Project;
import edu.kh.Achieve.project.model.vo.ProjectSign;

public class ProjectService {
	
	private ProjectDAO dao = new ProjectDAO();	

	/** 프로젝트 생성 Service
	 * @param project
	 * @return result
	 * @throws Exception
	 */
	public int createProject(Project project, int memberNo) throws Exception {
		
		Connection conn = getConnection();
		
		int projectNo  = dao.nextProjectNo(conn);
		
		project.setProjectNo(projectNo);
		
		int result = dao.createProject(conn,project, memberNo);
			
		if(result > 0) {
			result = dao.insertProject(conn, project, memberNo);
			
			for (int i = 1; i < 5; i++) dao.insertBoardType(conn, i, projectNo);
		}
		
		if(result > 0) commit(conn);
		else   			rollback(conn);
		
		return result;
	}

	
	/** 프로젝트 이름 중복검사 service
	 * @param projectName
	 * @return result
	 * @throws Exception
	 */
	public int PJDupCheck(String projectName) throws Exception {
		
		Connection conn = getConnection();
		
		int result2 = dao.PJDupCheck(conn, projectName);
		
		if(result2 > 0) commit(conn);
		else		   rollback(conn);
		
		
		
		
		return result2;
	}


	
	/** 프로젝트 공개/ 비공개 변경 service
	 * @param openStatus
	 * @return result
	 * @throws Exception
	 */
	public int changeStatus(String openStatus, int projectNo)throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.changeStatus(conn,openStatus, projectNo );
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		
		
		
		return result;
	}


	/** 프로젝트 소개 변경 service
	 * @param projectIntro
	 * @return result
	 * @throws Exception
	 */
	public int IntroEdit(String projectIntro, int projectNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.IntroEdit(conn, projectIntro, projectNo);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		
		
		return result;
	}


	
	public int changePJName(String projectName, int projectNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.changePJName(conn,projectName, projectNo);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		
		
		return result;
	}


	/**
	 * 프로젝트 찾기 목록 만드는 Service
	 * @param cp
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> searchAll(int cp, int memberNo) throws Exception{
		
		Connection conn = getConnection();
						
		// 전체 프로젝트 수 조회 DAO
		int listCount = dao.getListCount(conn);		
		
		
		// 전체 프로젝트 수 + 현재 페이지(cp)를 이용해 페이지네이션 객체
		Pagination pagination = new Pagination(cp, listCount);
		
		// 목록 조회
		List<Project> projectList = dao.selectProjectList(conn, memberNo, pagination);
		
		
		// Map 객체를 생성하여 두 결과 객체를 모두 저장
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("pagination", pagination);
		map.put("projectList", projectList);
		
		close(conn);
		
		return map; // Map 객체 반환
	}


	/**
	 * 프로젝트 찾기에서 검색한 경우
	 * @param cp
	 * @param key
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> searchProjectList(int cp, String key, String query, int memberNo) throws Exception{

		Connection conn = getConnection();
				
		// SQL 조건절에 추가될 구문(key, query 사용)
		String condition = null; // 조건
		
		switch(key) {
		case "t"  : condition = " AND PROJECT_NM LIKE '%" + query + "%' ";  break;
		case "c"  : condition = " AND M.MEMBER_NICK LIKE '%" + query + "%' ";  break;
		
		}
		
		// 리스트에서 조건을 만족하는 프로젝트 수 조회
		int listCount = dao.searchListCount(conn, condition);
				
		// listCount  + 현재 페이지(cp)를 이용해 페이지네이션 객체 생성
		Pagination pagination = new Pagination(cp, listCount);		
		
		
		// 조건을 만족하는 게시글 목록 조회
		List<Project> projectList = dao.searchProjectList(conn, pagination, condition, memberNo);
		
		// 결과를 하나의 Map에 모아서 반환
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("projectList", projectList);
		
		close(conn);
		
		return map;
	}


	
	
	/** PJ 가입승인 count Service
	 * @return list
	 * @throws Exception
	 */
	public List<ProjectSign> selectPJSign(int projectNo) throws Exception {
		
		Connection conn = getConnection();
		 
		List<ProjectSign> list = dao.selectPJSign(conn, projectNo);
		
		close(conn);
		
		
		return list;
	}


	
	
	/** PJ 가입승인 item불러오기 Service
	 * @param count
	 * @return count
	 * @throws Exception
	 */
	public int selectPJ(int projectNo) throws Exception {
		
		Connection conn = getConnection();
		
		int count = dao.selectPJ(conn, projectNo);
		
		close(conn);
		
		return count;
	}


	/** 가입승인 버튼 service
	 * @param memberNo
	 * @param projectNo
	 * @return result
	 * @throws Exception
	 */
	public int accountMember(int memberNo, int projectNo) throws Exception {
		
		
			
			Connection conn = getConnection();
			
			int result = dao.accountMember(conn,memberNo,projectNo );
			
			if(result > 0) commit(conn);
			else 		   rollback(conn);
		
		return result;
	}


	/** 전체 알림 발송 Service
	 * @param boardContent
	 * @param projectNo
	 * @return result
	 * @throws Exception
	 */
	public int insertNotice(String boardContent, int projectNo, int loginMemberNo) throws Exception {

		Connection conn = getConnection();
		
		List<Integer> projectMemberList = dao.selectProjectMemberList(conn, projectNo);
		
		int result = 0;
		
		for (int i = 0; i < projectMemberList.size(); i++) {
			
			int memberNo = projectMemberList.get(i);
			
			result = dao.insertNotice(conn, boardContent, memberNo, loginMemberNo);
		}
		
		if (result > 0) commit(conn);
		else  			rollback(conn);
		
		return result;
	}


	/*
	 * public int cancelAccount(int memberNo) throws Exception {
	 * 
	 * Connection conn = getConnection();
	 * 
	 * int result = dao.cancelAccount(conn,memberNo);
	 * 
	 * if(result > 0) commit(conn); else rollback(conn);
	 * 
	 * 
	 * 
	 * return result;
	 * 
	 * 
	 * 
	 * 
	 * }
	 */

}
