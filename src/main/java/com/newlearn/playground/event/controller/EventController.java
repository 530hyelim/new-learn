package com.newlearn.playground.event.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.newlearn.playground.event.service.EventService;
import com.newlearn.playground.event.vo.Event;
import com.newlearn.playground.event.vo.Event.EventType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/event")
public class EventController {
	@Autowired
	private EventService eventService;
	
	// 이벤트 페이지로 리디렉트 되거나 바로 이동했을때 오늘날짜로 뷰페이지 출력
	@GetMapping
	public String pageload(HttpSession session) {
		String selectedDate = session.getAttribute("year")+"-"+session.getAttribute("month")+"-"+session.getAttribute("today");
		return eventPage(selectedDate, session);
	}
	
	// 캘린더에서 날짜 클릭했을 때
	@GetMapping("/calendar")
	public String eventPage(@RequestParam String selectedDate, HttpSession session) {
		List<Event> sharedEvents = eventService.findAllByDate(selectedDate);
		List<Event> personalEvents = eventService.findAllPersonal(selectedDate);
		session.setAttribute("sharedEvents", sharedEvents);
		session.setAttribute("personalEvents", personalEvents);
		session.setAttribute("selectedDate", selectedDate);
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
	
	// 클래스룸 페이지에서 상세보기 버튼 클릭했을 때
	@GetMapping("/detail/{eventNo}")
	public String eventDetailMain(@PathVariable("eventNo") int eventNo, HttpSession session, Model model) {
		String selectedDate = null; 
		if (eventNo != 0) {
			Event selectedEvent = eventService.findByNo(eventNo);
			model.addAttribute("event", selectedEvent);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
			selectedDate = dateFormat.format(selectedEvent.getStartDate());
		}
		return eventPage(selectedDate, session);
	}
	
	// 날짜 형식 변환
	@InitBinder
	public void formatDatetime(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@GetMapping("/delete")
	public String deleteEvent(@RequestParam int eventNo, RedirectAttributes ra, HttpSession session) {
		// 삭제권한 체크
		int result = eventService.deleteEvent(eventNo);
		if (result == 1) {
			ra.addFlashAttribute("alertMsg", "일정이 정상적으로 삭제되었스비낟.");
		} else {
			ra.addFlashAttribute("alertMsg", "일정을 삭제하는 도중 에러가 발생하였습니다.");
		}
		return "redirect:/mypage"+session.getAttribute("loginUserNo");
	}
	
	// 이벤트 생성 or 수정
	@PostMapping("/dml")
	public String submitEvent(@ModelAttribute("event") Event event, HttpSession session, RedirectAttributes ra) {
		// 수정권한 체크
		// 논리검사
		// 1. endDate가 startDate보다 더 이전이라면
		if (event.getEndDate().compareTo(event.getStartDate()) < 0) {
			ra.addFlashAttribute("alertMsg", "종료날짜는 시작날짜보다 빠를 수 없습니다.");
			if (event.getEventType() == EventType.PERSONAL) return "redirect:/mypage/"+session.getAttribute("loginUserNo");
			return "redirect:/event/detail/"+event.getEventNo();
		}
		// 2. endDate가 오늘 날짜보다 더 이전이라면
		if (event.getEndDate().compareTo(new Date()) < 0) {
			ra.addFlashAttribute("alertMsg", "종료날짜는 오늘날짜보다 빠를 수 없습니다.");
			if (event.getEventType() == EventType.PERSONAL) return "redirect:/mypage/"+session.getAttribute("loginUserNo");
			return "redirect:/event/detail/"+event.getEventNo();
		}
		// 3. joinDeadline이 startDate보다 더 이전이라면
		if (event.getEventType() == EventType.SHARED && event.getJoinDeadline().compareTo(event.getStartDate()) < 0) {
			ra.addFlashAttribute("alertMsg", "이벤트 참여기한은 시작날짜보다 빠를 수 없습니다.");
			if (event.getEventType() == EventType.PERSONAL) return "redirect:/mypage/"+session.getAttribute("loginUserNo");
			return "redirect:/event/detail/"+event.getEventNo();
		}
		// 4. 캘린더 테이블에 시작일, 종료일을 속성으로 추가했는데, 새 일정 입력할 때 원래 예정되어 있지 않은 날짜에만 일정을 추가할 수 있게 하려면? 
		// 예를들어 1월1일 오후 1시부터 3시까지 이미 약속이 있는데 2시부터 4시까지 일정을 추가하려고 할 때 어떻게 해야할까요?
		if (event.getDmlType().equals("insert")) {
			event.setClassNo((int)session.getAttribute("classNo"));
			event.setUserNo((int)session.getAttribute("loginUserNo"));
			if (event.getEventType() == EventType.PERSONAL) event.setJoinDeadline(new Date()); // nullable 변경?
			int result = eventService.insertEvent(event);
			if (result == 1) ra.addFlashAttribute("alertMsg", "새 이벤트가 정상적으로 추가되었습니다.");
		}
		if (event.getDmlType().equals("update")) {
			if (event.getEventType() == EventType.PERSONAL) event.setJoinDeadline(new Date()); // nullable 변경?
			int result = eventService.updateEvent(event);
			if (result == 1) ra.addFlashAttribute("alertMsg", "이벤트가 정상적으로 수정되었습니다.");
		}
		if (event.getEventType() == EventType.PERSONAL) return "redirect:/mypage/"+session.getAttribute("loginUserNo");
		return "redirect:/event";
	}
	
}
