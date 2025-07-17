package com.newlearn.playground.event.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlearn.playground.event.service.EventService;
import com.newlearn.playground.event.vo.Event;

@Controller
@RequestMapping("/event")
public class EventController {
	@Autowired
	private EventService eventService;
	
	@GetMapping
	public String eventPage(@RequestParam String selectedDate, Model model) {
		String[] parts = selectedDate.split("-");
		Date date = new Date(Integer.parseInt(parts[0])-1900, 
				Integer.parseInt(parts[1])-1, Integer.parseInt(parts[2]));
		List<Event> sharedEvents = eventService.findAllByDate(date);
		model.addAttribute("sharedEvents", sharedEvents);
		model.addAttribute("selectedDate", selectedDate);
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
	
	@PostMapping("/new")
	public String submitEvent(@ModelAttribute("event") Event event) {
		eventService.insertEvent(event);
		return "redirect:/event";
	}
}
