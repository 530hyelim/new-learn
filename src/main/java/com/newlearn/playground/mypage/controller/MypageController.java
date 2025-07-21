package com.newlearn.playground.mypage.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlearn.playground.event.service.EventService;
import com.newlearn.playground.event.vo.Event;
import com.newlearn.playground.mypage.model.vo.Guestbook;
import com.newlearn.playground.mypage.service.MypageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {
	private final ServletContext application; 
	private final MypageService mypageService;
	private final EventService eventService;
	
	@GetMapping
	public String myPage(Model model) {
		String imgDir = application.getRealPath("/resources/main");
		File path = new File(imgDir);
		if (path.exists()) {
			File[] files = path.listFiles();
			List<String> fileList = Arrays.stream(files)
		            .map(File::getName)
		            .collect(Collectors.toList());
		    model.addAttribute("fileList", fileList);
		}
		return "mypage/mypage";
	}
	
	@GetMapping("/guestbook")
	public String loadGuestbook(Model model) {
		int mypageNo = 1; // 마이페이지 사용자 정보
		List<Guestbook> gbList = mypageService.loadGuestbook(mypageNo);
		model.addAttribute("gbList", gbList);
		return "mypage/guestbook";
	}
	
	@GetMapping("/calendar")
	public String loadCalendar(HttpSession session, Model model) {
		String selectedDate = (int)session.getAttribute("year") + "-";
		selectedDate += (int)session.getAttribute("month") + "-";
		selectedDate += (int)session.getAttribute("today");
		List<Event> personalEvents = eventService.findAllPersonal(selectedDate);
		model.addAttribute("personalEvents", personalEvents);
		model.addAttribute("selectedDate", selectedDate);
		return "mypage/calendar";
	}
	
	@GetMapping("/calendar/{date}")
	public String loadCalendar(@PathVariable("date") String date, HttpSession session, Model model) {
		List<Event> personalEvents = eventService.findAllPersonal(date);
		model.addAttribute("personalEvents", personalEvents);
		model.addAttribute("selectedDate", date);
		return "mypage/calendar";
	}
	
	@GetMapping("/calendar/new")
	public String insertCalendar(Model model) {
		
		return "mypage/calendar";
	}
	
	@GetMapping("/storage")
	public String loadStorage(Model model) {
		
		return "mypage/storage";
	}
}
