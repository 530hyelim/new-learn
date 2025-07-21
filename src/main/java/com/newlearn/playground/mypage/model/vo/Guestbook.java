package com.newlearn.playground.mypage.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Guestbook {
	private int guestbookNo;
	private int mypageNo;
	private int userNo;
	private String content;
	private Date createDate;
	private String visibilitiy;
	private String deleted;
	
	private String userName;
}
