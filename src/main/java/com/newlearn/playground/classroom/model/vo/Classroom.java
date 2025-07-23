package com.newlearn.playground.classroom.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Classroom {
	private int classNo;
	private String className;
	private String entryCode;
	private Date createDate;
	private String deleted;
	private String classCode;
}
