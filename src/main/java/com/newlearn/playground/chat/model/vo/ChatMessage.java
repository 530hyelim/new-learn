package com.newlearn.playground.chat.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ChatMessage {
	private int cmNo;
	private String message;
	private String createDate;
	private int chatRoomNo;
	private int userNo;
	
	// 클라이언트의 메시지 유형을 관리할 속성
	public enum MessageType {
		ENTER, EXIT, TALK
	}
	
	private MessageType type;
	
	private String userName;
}
