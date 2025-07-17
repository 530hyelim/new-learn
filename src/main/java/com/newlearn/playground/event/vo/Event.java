package com.newlearn.playground.event.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Event {
	private int eventNo;
	private int classNo;
	private int userNo;
	private String eventName;
	private Date startDate;
	private Date endDate;
	private String content;
	private String place;
	private Date createDate;

	public enum EventType {
		SHARED, PERSONAL
	}
	private EventType eventType;
	
	private String visibility;
	private int numPpl;
	private Date joinDeadline;
}
