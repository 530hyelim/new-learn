package com.newlearn.playground.event.dao;

import java.util.List;

import com.newlearn.playground.event.vo.Event;

public interface EventDao {

	List<Event> findAllByDate(String selectedDate);

	Event findByNo(int eventNo);

	List<Event> findAllPersonal(String selectedDate);

	List<Event> upcomingEvents(String selectedDate);

	Integer joinMemberCnt(int eventNo);

	int insertEvent(Event event);

	int updateEvent(Event event);

}
