package com.newlearn.playground;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newlearn.playground.event.service.EventService;
import com.newlearn.playground.event.vo.Event;

@Controller
public class MainController {
	@Autowired
	private EventService eventService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session, Model model) {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int today = calendar.get(Calendar.DATE);
		
		calendar.set(year, month-1, 1);
		int startDay = calendar.get(Calendar.DAY_OF_WEEK);
		int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		List<Integer> days = new ArrayList<>();
		for (int day = 1; day <= lastDay; day++) {
			days.add(day);
		}
		session.setAttribute("year", year);
		session.setAttribute("month", month);
		session.setAttribute("today", today);
		session.setAttribute("startDay", startDay);
		session.setAttribute("lastDay", lastDay);
		session.setAttribute("days", days);
		
		String selectedDate = year + "-" + month + "-" + today;
		List<Event> upcomingEvents = eventService.upcomingEvents(selectedDate);
		Map<Event, Integer> eventWithMemberCnt = new HashMap<>();
		for (Event event : upcomingEvents) {
			eventWithMemberCnt.put(event, eventService.joinMemberCnt(event.getEventNo()));
		}
		model.addAttribute("upcomingEvents", upcomingEvents);
		model.addAttribute("eventWithMemberCnt", eventWithMemberCnt);
		return "main";
	}
	
}
