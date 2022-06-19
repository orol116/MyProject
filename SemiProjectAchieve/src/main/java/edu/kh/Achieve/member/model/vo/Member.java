package edu.kh.Achieve.member.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Member {
	
	private int memberNo;
	private String memberEmail;
	private String memberPw;
	private String memberName;
	private String memberNickname;
	private String memberBirthday;
	private String memberTel;
	private String secessionFlag;
	private String suspensionFlag;
	private String profileImage;
	


}
