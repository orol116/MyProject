package edu.kh.Achieve.project.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class ProjectSign {
	
	private int accountNo;
	private int memberNo;
	private int projectNo;
	private String accountFlag;
	private String memberNickname;
	private String profileImage;

}
