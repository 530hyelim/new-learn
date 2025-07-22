package com.newlearn.playground.chat.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendDTO {
	private int userNo;
	private String userName;
	private String statusMessage;
	private String changeName;
	private List<String> classNameList;
	private int friendUserNo;
	private int cnt;
}
