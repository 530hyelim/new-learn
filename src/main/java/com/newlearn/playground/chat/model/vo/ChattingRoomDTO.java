package com.newlearn.playground.chat.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChattingRoomDTO {
	private int chatRoomNo;
	private String chatTitle;
	private String chatPublic;
	private String userName;
	private String changeName;
	private int participantCount;
	private String roomNoti;
	private int unreadCount;
}
