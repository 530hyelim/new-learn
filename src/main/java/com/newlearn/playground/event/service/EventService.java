package com.newlearn.playground.event.service;

import java.util.List;

import com.newlearn.playground.event.vo.Event;

public interface EventService {

	Event findByNo(int eventNo);

	void insertEvent(Event event);

	List<Event> findAllByDate(String selectedDate);

	List<Event> findAllPersonal(String selectedDate);

	List<Event> upcomingEvents(String selectedDate);

	Integer joinMemberCnt(int eventNo);

}
