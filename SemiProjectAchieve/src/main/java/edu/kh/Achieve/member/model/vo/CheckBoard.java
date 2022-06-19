package edu.kh.Achieve.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CheckBoard {

		private String boardTitle;
		private String boardContent;
		private String createDate;
		private String updateDate;
		private int boardNo;
		private int readCount;
		private String boardState;
		private int memberNo;
		private String memberNickname;
		private String profileImage;
		private int projectNo;
		


	}


