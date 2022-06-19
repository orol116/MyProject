package edu.kh.Achieve.project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.Achieve.member.model.vo.Member;
import edu.kh.Achieve.project.model.service.ProjectService;
import edu.kh.Achieve.project.model.vo.Project;

@WebServlet("/project/PJCreate")
public class PJCreateServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String path = "/WEB-INF/views/project/PJCreate.jsp";
		
		req.getRequestDispatcher(path).forward(req, resp);
		
	}
	
	/** 프로젝트 생성 Servlet
	 *
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String projectName = req.getParameter("projectName");
		String projectQuota = req.getParameter("projectQuota");
		String openStatus = req.getParameter("openStatus");
		String projectIntro = req.getParameter("projectIntro");

		Project project = new Project();
		
		project.setProjectName(projectName);
		project.setProjectQuota(projectQuota);
		project.setOpenStatus(openStatus);
		project.setProjectIntro(projectIntro);
		
		HttpSession session = req.getSession();
		
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		
		int memberNo = loginMember.getMemberNo();
		
		try {
		
			ProjectService service = new ProjectService();
			
			int result = service.createProject(project, memberNo);
			
			if(result > 0) {
				session.setAttribute("message", "프로젝트가 생성되었습니다");
			}else {
				session.setAttribute("message", "프로젝트가 생성되지 않았습니다.");
			}
			
			resp.sendRedirect(req.getContextPath());
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}

		
	}
	

}