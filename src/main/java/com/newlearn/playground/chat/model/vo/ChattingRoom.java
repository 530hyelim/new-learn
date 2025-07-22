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
	 * 방 번호
	 * 방 제목
	 * 개설자
	 * 개설자 사진
	 * 새소식 수
	 * 참여자 수
	 * 공개 여부
	 * 알림 여부
	 */
	private int createrNo;
	private String createrName;
	private String createrProfileImg;
	private String roomTitle;
	private int JoinnerCount;
	private String roomPublic;
	private String roomPassworld;
	private String roomAlarm;
}	