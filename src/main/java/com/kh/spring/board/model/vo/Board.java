package com.kh.spring.board.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Board {
	private int boardNo;
	private int classNo;
	private int userNo;
	private String category;
	private String boardTitle; // userNo, userName
	private String boardContent;
	private Date createDate;
	private String updateDate;
	private int viewCount;
	private int likeCount;
	private String boardStatus;
}









