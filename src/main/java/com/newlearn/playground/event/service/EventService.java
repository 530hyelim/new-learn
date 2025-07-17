package com.newlearn.playground.event.service;

import java.util.Date;
import java.util.List;

import com.newlearn.playground.event.vo.Event;

public interface EventService {

	Event findByNo(int eventNo);

	void insertEvent(Event event);

	List<Event> findAllByDate(Date date);

}
