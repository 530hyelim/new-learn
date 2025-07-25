package com.newlearn.playground.event.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlearn.playground.event.dao.EventDao;
import com.newlearn.playground.event.vo.Event;

@Service
public class EventServiceImpl implements EventService {
	@Autowired
	private EventDao eventDao;
	
	@Override
	public Event findByNo(int eventNo) {
		return eventDao.findByNo(eventNo);
	}

	@Override
	public List<Event> findAllByDate(String selectedDate) {
		return eventDao.findAllByDate(selectedDate);
	}

	@Override
	public List<Event> findAllPersonal(Map<String, String> paramMap) {
		return eventDao.findAllPersonal(paramMap);
	}

	@Override
	public List<Event> upcomingEvents() {
		return eventDao.upcomingEvents();
	}

	@Override
	public Integer joinMemberCnt(int eventNo) {
		return eventDao.joinMemberCnt(eventNo);
	}

	@Override
	public int insertEvent(Event event) {
		return eventDao.insertEvent(event);
	}
	
	@Override
	public int updateEvent(Event event) {
		return eventDao.updateEvent(event);
	}

	@Override
	public int deleteEvent(int eventNo) {
		return eventDao.deleteEvent(eventNo);
	}

}
