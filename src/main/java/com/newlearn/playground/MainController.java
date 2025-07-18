package com.newlearn.playground;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session) {
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
		return "main";
	}
	
}
