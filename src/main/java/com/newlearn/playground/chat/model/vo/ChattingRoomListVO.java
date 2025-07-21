package com.newlearn.playground.chat.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChattingRoomListVO {
	/*
	 * 1. 사용자 번호 -> 친구 번호, 속한 클래스 번호, 속한 채팅방 번호
	 * 
	 * 2. 친구 번호 -> 친구의 프로필사진, 이름, 상태메세지, 속한 클래스 번호
	 * 3. 클래스 번호 -> 사용자와 친구가 같이 속한 클래스 이름
	 * 
	 * 4. 속한 채팅방 번호 -> 개설자, 개설자 사진, 방 제목, 새 소식 수, 참여자 수, 공개여부, 알림여부
	 * 
	 * 5. 기존 방 제목, 변경할 방 제목, 속하지 않은 친구
	 */
	private int userNo; // 사용자 번호
	private int classNo; // 속한 클래스 번호
	private int chatNo; // 속한 채팅방 번호
	
	private int friendUserNo; // 친구 번호
	private int friendImg; // 친구 프로필사진
	private String friendName; // 친구 이름
	private String friendStatusMessage; // 친구 상태메세지
	private String friendClassNo; // 친구가 속한 클래스 번호
	
	private String chatCreater; // 채팅방 개설자
	private int chatCreaterImg; // 채팅방 개설자 프로필사진
	private String chatTitle; // 채팅방 제목
	private int newMessage; // 안읽은 메세지
	private int chatMember; // 구성원 수
	private int openClose; // 공개 여부
	private int alarm; // 알림 여부
	
	
	
	
}
