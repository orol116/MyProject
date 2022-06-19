package edu.kh.Achieve.board.model.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDetail {

	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String createDate;
	private String updateDate;
	private int readCount;
	private String memberNickname;
	private String profileImage;
	private int memberNo;
	private String boardName;
	
	// 프로젝트 번호
	private int projectNo;
	
	// 첨부파일
	private List<BoardAttachment> attachmentList;
}

