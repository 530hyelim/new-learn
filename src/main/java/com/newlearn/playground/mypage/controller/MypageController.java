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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/{mypageNo}")
	public String myPage(@PathVariable("mypageNo") int mypageNo, HttpSession session, Model model) {
		String imgDir = application.getRealPath("/resources/main");
		File path = new File(imgDir);
		if (path.exists()) {
			File[] files = path.listFiles();
			List<String> fileList = Arrays.stream(files)
		            .map(File::getName)
		            .collect(Collectors.toList());
		    model.addAttribute("fileList", fileList);
		}
		session.setAttribute("mypageNo", mypageNo);
		return "mypage/mypage";
	}
	
	@GetMapping("/guestbook")
	public String loadGuestbook(HttpSession session, Model model) {
		int mypageNo = (int)session.getAttribute("mypageNo");
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
	
	@GetMapping("/storage")
	public String loadStorage(Model model) {
		
		return "mypage/storage";
	}
	
	@PostMapping("/guestbook/hide")
	public String guestbookHide(@RequestParam int guestbookNo) {
		int result = mypageService.guestbookHide(guestbookNo);
		return "redirect:/mypage";
	}
	
	@PostMapping("/guestbook/delete")
	public String guestbookDelete(@RequestParam int guestbookNo) {
		int result = mypageService.guestbookDelete(guestbookNo);
		return "redirect:/mypage";
	}
	
	@PostMapping("/guestbook/new")
	public String guestbookInsert(@RequestParam String content, @RequestParam int mypageNo, 
			@RequestParam int userNo, @RequestParam String userName) {
		Guestbook g = new Guestbook();
		g.setContent(content);
		g.setMypageNo(mypageNo);
		g.setUserNo(userNo);
		g.setUserName(userName);
		int result = mypageService.guestbookInsert(g);
		return "redirect:/mypage";
	}
}
