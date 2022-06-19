package edu.kh.Achieve.project.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class Project {
	
	private int projectNo;
	private String projectName;
	private int projectManager;
	private String projectQuota;
	private String openStatus;
	private String projectIntro;

	private String memberNickName;
	private String projectNM;
	private int boardNo;
	
	private int memberNo;
	
	private String projectManagerNickname;
	
	private String participateStatus; // 특정 회원의 프로젝트 가입 여부 
}

