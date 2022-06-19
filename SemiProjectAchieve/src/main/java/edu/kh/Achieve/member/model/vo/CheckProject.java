package edu.kh.Achieve.member.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CheckProject {
	
	private int memberNo;
	private String memberNickName;
	private int projectNo;
	private String projectNM;
	private int boardNo;

}
