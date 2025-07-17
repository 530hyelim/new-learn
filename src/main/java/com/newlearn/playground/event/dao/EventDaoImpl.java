package com.newlearn.playground.event.dao;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlearn.playground.event.vo.Event;

@Repository	
public class EventDaoImpl implements EventDao {
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<Event> findAllByDate(Date date) {
		return session.selectList("event.findAllByDate", date);
	}

}
