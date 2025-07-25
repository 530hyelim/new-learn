package com.newlearn.playground.event.dao;

import java.util.List;
import java.util.Map;

import com.newlearn.playground.event.vo.Event;

public interface EventDao {

	List<Event> findAllByDate(String selectedDate);

	Event findByNo(int eventNo);

	List<Event> findAllPersonal(Map<String, String> paramMap);

	List<Event> upcomingEvents();

	Integer joinMemberCnt(int eventNo);

	int insertEvent(Event event);

	int updateEvent(Event event);

	int deleteEvent(int eventNo);

}
