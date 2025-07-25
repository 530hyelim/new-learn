package com.newlearn.playground.event.service;

import java.util.List;
import java.util.Map;

import com.newlearn.playground.event.vo.Event;

public interface EventService {

	Event findByNo(int eventNo);

	int insertEvent(Event event);

	List<Event> findAllByDate(String selectedDate);

	List<Event> findAllPersonal(Map<String, String> paramMap);

	List<Event> upcomingEvents();

	Integer joinMemberCnt(int eventNo);

	int updateEvent(Event event);

	int deleteEvent(int eventNo);

}
