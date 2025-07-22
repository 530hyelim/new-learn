package com.newlearn.playground;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.newlearn.playground.classroom.model.vo.Attendance;
import com.newlearn.playground.classroom.model.vo.Classroom;
import com.newlearn.playground.classroom.service.ClassroomService;
import com.newlearn.playground.event.service.EventService;
import com.newlearn.playground.event.vo.Event;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	private final EventService eventService;
	private final ClassroomService classroomService;
	
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
		
		// 테스트용
		int loginUserNo = 1;
		String loginUserName = "서혜림";
		int classNo = 1;
		String className = "KH 자바스터디 G반";
		
		session.setAttribute("loginUserNo", loginUserNo);
		session.setAttribute("loginUserName", loginUserName);
		session.setAttribute("classNo", classNo);
		session.setAttribute("className", className);
		
		Attendance a = new Attendance();
		a.setUserNo(loginUserNo);
		a.setClassNo(classNo);
		a = classroomService.getAttendance(a);
		if (a != null) session.setAttribute("attendance", a);
		return "main";
	}
	
	@PostMapping("/entry")
	public String entry(@RequestParam String attEntryCode, @RequestParam int classNo, 
			@RequestParam int userNo, HttpSession session, RedirectAttributes ra) {
		// 이미 입실한 경우
		if (session.getAttribute("attendance") != null) {
			ra.addFlashAttribute("entryMsg", "이미 입실완료 되었습니다.");
			return "redirect:/";
		}
		// 입실코드가 틀린 경우
		Classroom c = classroomService.getClassroom(classNo);
		if (!attEntryCode.equals(c.getAttEntryCode())) {
			ra.addFlashAttribute("entryMsg", "잘못된 코드입니다.");
			return "redirect:/";
		}
		// attendance db에 데이터 추가하고 세션에 저장
		Attendance a = new Attendance();
		a.setUserNo(userNo);
		a.setClassNo(classNo);
		int result = classroomService.addAttendance(a);
		if (result == 1) {
			session.setAttribute("attendance", classroomService.getAttendance(a));
			ra.addFlashAttribute("entryMsg", "정상적으로 입실처리 되었습니다.");
		}
		return "redirect:/";
	}
}
