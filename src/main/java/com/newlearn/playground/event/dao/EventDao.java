package com.newlearn.playground.event.dao;

import java.util.Date;
import java.util.List;

import com.newlearn.playground.event.vo.Event;

public interface EventDao {

	List<Event> findAllByDate(Date date);

}
