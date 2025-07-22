package com.newlearn.playground.chat.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChattingRoom {
	/* 채팅방
	 * 
	 * 개설자 번호
	 * 개설자 이름
	 * 개설자 사진
	 * 채팅방 번호
	 * 채팅방 제목
	 * 새 소식 수
	 * 참여자 수
	 * 공개 여부
	 * 알림 여부
	 */
	private int createrNo;
	private String createrName;
	private String createrProfileImg;
	private int roomNo;
	private String roomTitle;
	private int JoinnerCount;
	private String roomPublic;
	private String roomPassworld;
	private String roomAlarm;
}	