package edu.kh.Achieve.member.model.vo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Note {
	private int noteNo;
	private String noteContent;
	private Date noteDate;
	private int senderNo;
	private int receiverNo;
	
	private String sender;
	
}
