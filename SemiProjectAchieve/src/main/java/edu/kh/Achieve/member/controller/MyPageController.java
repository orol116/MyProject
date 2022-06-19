package edu.kh.Achieve.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import edu.kh.Achieve.common.MyRenamePolicy;
import edu.kh.Achieve.member.model.service.MemberService;
import edu.kh.Achieve.member.model.vo.Member;

@WebServlet("/member/myPage/*") // myPage로 시작하는 모든 요청을 받음
public class MyPageController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring( (contextPath + "/member/myPage/").length() );
		
		MemberService service = new MemberService();
		
		HttpSession session = req.getSession();
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		int memberNo = loginMember.getMemberNo();
		
		session.setAttribute("memNo", memberNo);
		
		try {
			
			if(command.equals("info")) { // 내 정보 수정
				
				String path = "/WEB-INF/views/member/myPage-info.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
				
				
			} // info if문 끝
			
			
			
			if(command.equals("update")) {
				
				
				// 파라미터 얻어오기(이름, 닉네임, 핸드폰번호)
				String memberName = req.getParameter("memberName");
				String memberNickname = req.getParameter("memberNickname");
				String memberTel = req.getParameter("memberTel");
				
				// 수정에 필요한 정보를 모아둔 Member 객체 생성
				Member mem = new Member();
				
				mem.setMemberNo(memberNo);
				mem.setMemberName(memberName);
				mem.setMemberNickname(memberNickname);
				mem.setMemberTel(memberTel);
				
	
				
				// 회원 정보 수정 후 결과 반환
				int result = service.updateMember(mem);
				
				// 수정 성공/실패에 따른 메세지 출력 제어
				if(result > 0) {
					session.setAttribute("message", "회원 정보 수정 완료");
					
					// session의 로그인 회원 정보 수정 (DB와 동일하게)
					loginMember.setMemberName(memberName);
					loginMember.setMemberNickname(memberNickname);
					loginMember.setMemberTel(memberTel);
				} else {
					session.setAttribute("message", "회원 정보 수정 실패");
				}
				
				
				// 결과 상관 없이 내 정보 화면 재요청
				resp.sendRedirect(req.getContextPath() + "/member/myPage/info");
				
			} // update if문 끝
			
			
			// -------------------------------------------------------------
			
			
			if(command.equals("changePw")) { // 비밀번호 변경
				
				String path = "/WEB-INF/views/member/myPage-changePw.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
				
				
			} // changePw 화면 전환 if문 끝
			
			
			if(command.equals("changePwSubmit")) { // 비밀번호 변경
				// 파라미터 얻어오기(현재 비밀번호, 새 비밀번호)
				String currentPw = req.getParameter("currentPw");
				String newPw = req.getParameter("newPw");
				
				
				
				// 비밀번호 변경 후 결과 반환
				int result = service.changePw(currentPw, newPw, memberNo);
				
				
				// 리다이렉트 주소
				String path = null;
				
				if(result > 0) {
					session.setAttribute("message", "비밀번호 변경 완료");
					path = req.getContextPath() + "/member/myPage/info";
					loginMember.setMemberPw(newPw);
				} else {
					session.setAttribute("message", "비밀번호가 일치하지 않습니다.");
					path = req.getContextPath() + "/member/myPage/changePw";
				}

				// 결과에 따라 화면 재요청
				resp.sendRedirect(path);
				
			} // changePwSubmit if문 끝
			
			
			// ------------------------------------------------------
			
			
			
			if(command.equals("secession")) { // 회원 탈퇴 화면 전환
				
				String path = "/WEB-INF/views/member/myPage-secession.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
				
				
			} // secession 화면 전환 if문 끝
			
			
			if(command.equals("secessionSubmit")) {
				
				// 회원 탈퇴 후 결과 반환
				int result = service.secession(memberNo);
				String path = null;
				
				if(result > 0) {
					session.invalidate(); // 세션 무효화
					session = req.getSession(); // 새로운 세션 얻어오기
					
					session.setAttribute("message", "회원 탈퇴 성공");
					path = req.getContextPath();
					
					Cookie c = new Cookie("saveId", ""); // 쿠키 생성
					c.setMaxAge(0); // 쿠키 수명
					c.setPath(req.getContextPath()); // 쿠키 적용 경로
					resp.addCookie(c); // 쿠키 클라이언트에 전송
				} else {
					session.setAttribute("message", "회원 탈퇴 실패, 메인 페이지로 돌아갑니다.");
					path = req.getContextPath();
				}
				
				resp.sendRedirect(path);
				
			} // secessionSubmit if문 끝
			
			
			// ------------------------------------------------------
			
			
			if(command.equals("profile")) {
				
				String path = "/WEB-INF/views/member/myPage-profile.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
				
			} // profile if문 끝
			
			
			if(command.equals("profileSubmit")) {
				
				int maxSize = 1024 * 1024 * 20;
				//			   1KB    1MB   20MB

				String root = session.getServletContext().getRealPath("/");
				// C:\workspace\SemiProject_Achieve\src\main\webapp
				
				// 실제 파일이 저장되는 폴더의 경로
				String folderPath = "/resources/images/memberProfile/";
				
				// memberProfile 폴더까지의 절대 경로
				// C:\workspace\SemiProject_Achieve\src\main\webapp\resources\images\memberProfile
				String filePath = root + folderPath;
				
				// 저장되는 파일의 파일명 중복 방지를 위한 파일명 변경 규칙
				// --> MyRenamePolicy 클래스 생성
				
				// 파일 이외의 파라미터들의 문자 인코딩 지정
				String encoding = "UTF-8";
				
				MultipartRequest mpReq = new MultipartRequest(req, filePath, maxSize, encoding, new MyRenamePolicy());

				// DB에 삽입 될 프로필 이미지 경로
				// 단, x 버튼이 클릭되면(input태그 value값이 1이면) null 대입
				String profileImage = folderPath + mpReq.getFilesystemName("profileImage");		
				
				// ** 프로필 이미지 삭제 **
				// 1) "delete" input type="hidden" 태그의 값(파라미터) 얻어 오기
				int delete = Integer.parseInt(mpReq.getParameter("delete"));
				
				// 2) delete의 값이 1(눌린 경우) 이면 profileImage의 값을 null로 변경
				if(delete == 1) {
					profileImage = null;
				}
				
				// 프로필 이미지 변경 Service 호출 후 결과 반환 받기
				int result = service.updateProfileImage(memberNo, profileImage);
				
				if(result > 0) { // 성공
					session.setAttribute("message", "프로필 이미지가 변경되었습니다.");
					
					// session에 저장된 로그인 정보 동기화 작업 진행
					loginMember.setProfileImage(profileImage);
					
				} else { // 실패
					session.setAttribute("message", "프로필 이미지 변경 실패");				
				}
				
				// 성공/실패 관계 없이 프로필 화면 재요청
				resp.sendRedirect("profile"); // 상대경로
			}
			
			
			// ------------------------------------------------------
			
		} catch(Exception e) {
			e.printStackTrace();
			
			// ajax error 속성 활용을 위해 강제로 500에러 전달
			resp.setStatus(500); // 500번 : 서버 에러
			
			// 에러 내용 출력
			resp.getWriter().print(e.getMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
