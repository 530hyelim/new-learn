package com.newlearn.playground.event.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlearn.playground.event.service.EventService;
import com.newlearn.playground.event.vo.Event;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/event")
public class EventController {
	@Autowired
	private EventService eventService;
	
	@GetMapping
	public String eventPage(@RequestParam String selectedDate, HttpSession session, Authentication auth) {
		List<Event> sharedEvents = eventService.findAllByDate(selectedDate);
		session.setAttribute("sharedEvents", sharedEvents);
		session.setAttribute("selectedDate", selectedDate);
		session.setAttribute("loginUser", auth.getName());
		log.debug(auth.getName());
		return "event/event";
	}
	
	// 추가 버튼 클릭 시 빈 폼 요청
	@GetMapping("/new")
	public String insertEvent(Model model) {
		model.addAttribute("event", new Event());
		return "event/event";
	}
	
	// 상세 이벤트 보기 요청
	@GetMapping("/detail")
	public String eventDetail(@RequestParam int eventNo, Model model) {
		if (eventNo != 0) {
			Event selectedEvent = eventService.findByNo(eventNo);
			model.addAttribute("event", selectedEvent);
		}
		return "event/event";
	}
	
	// 날짜 형식 변환
	@InitBinder
	public void formatDatetime(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@PostMapping("/new")
	public String submitEvent(@ModelAttribute("event") Event event) {
		eventService.insertEvent(event);
		return "redirect:/event";
	}
}
