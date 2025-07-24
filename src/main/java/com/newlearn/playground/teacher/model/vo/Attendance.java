package com.newlearn.playground.teacher.model.vo;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Attendance {
	private int userNo;
	private int classNo;
	private Date entryTime;
	private Date exitTime;
	private String attStatus;
}
