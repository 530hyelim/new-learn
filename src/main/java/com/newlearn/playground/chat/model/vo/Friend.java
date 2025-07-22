package com.newlearn.playground.chat.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friend {
	/* 친구
	 * 
	 * 유저번호
	 * 이름
	 * 프로필사진
	 * 상태메세지
	 * 속한 클래스
	 */
	private int userNo;
	
	@Data
	@NoArgsConstructor
	public static class FriendDTO{
		private String userName;
		private String statusMessage;
		private String changeName;Z
		private List<String> classNameList;
		private int FriendUserNo;
		private int cnt;
	}
}
