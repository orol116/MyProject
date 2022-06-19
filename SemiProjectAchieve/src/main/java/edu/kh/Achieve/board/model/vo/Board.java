package edu.kh.Achieve.board.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
public class Board {
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String createDate;
	private String updateDate;
	private int readCount;
	private String boardState;
	private int memberNo;
	private String memberNickname;
	private String boardName;
	private int boardCode;

	private String profileImage;
	private int projectNo;
	
	
}
