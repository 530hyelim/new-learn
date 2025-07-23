package com.newlearn.playground.mypage.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Repository {
	private int repoNo;
	private int mypageNo;
	private String dirName;
	private int parentRepoNo;
}
