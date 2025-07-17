package com.newlearn.playground.event.service;

import java.util.Date;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Event> findAllByDate(Date date) {
		return eventDao.findAllByDate(date);
	}

}
