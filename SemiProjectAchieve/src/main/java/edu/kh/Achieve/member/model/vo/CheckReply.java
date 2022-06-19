package edu.kh.Achieve.member.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CheckReply {
	
	private int replyNo;
	private int boardNo;
	private String replyContent;
	private String replyDate;
	private int memberNo;
	private String memberNickName;
	private String profileImage;
	private String boardTitle;
	private int projectNo;
	

}
