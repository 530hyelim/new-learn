package com.newlearn.playground.ai.model.vo;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AiChatSession {
	private int sessionNo;
	private String title;
	private Date createdAt; // sql Date 타입임.
	private int userNo;
	private int modelNo;
	private Date lastUsedAt; // sql Date 타입임.
}
