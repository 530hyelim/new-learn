package com.newlearn.playground.chat.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChattingRoom {
	/*
	 * 1. 채팅방 번호로 
	 * 3. 친구 번호로 프로필사진, 이름, 상태메세지, 속한 클래스 조회
	 * 
	 * 3. 사용자 번호로 속한 채팅방 번호 조회
	 * 4. 채팅방 번호로 채팅방에 속한 사람, 개설자, 방 제목, 참여자 수, 공개여부, 알림여부 조회
	 */
	private int userNo; // 사용자 번호
	private int friendUserNo; // 친구 번호
	private String userName; // 친구 이름
	private int imgNo; // 친구 프로필사진
	private String statusMessage; // 친구 상태메세지
	private int classNo; // 클래스 번호
	private String className; // 클래스 이름
}	