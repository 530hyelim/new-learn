package com.newlearn.playground.event.service;

import java.util.List;

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
	public void insertEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Event> findAllByDate(String selectedDate) {
		return eventDao.findAllByDate(selectedDate);
	}

	@Override
	public List<Event> findAllPersonal(String selectedDate) {
		return eventDao.findAllPersonal(selectedDate);
	}

	@Override
	public List<Event> upcomingEvents(String selectedDate) {
		return eventDao.upcomingEvents(selectedDate);
	}

	@Override
	public Integer joinMemberCnt(int eventNo) {
		return eventDao.joinMemberCnt(eventNo);
	}

}
