package edu.kh.Achieve.board.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardAttachment {
	
	private int attachmentNo;
	private String attachmentReName;
	private String attachmentOriginal;
	private int attachmentLevel;
	private int boardNo;
	private int projectNo;

}
