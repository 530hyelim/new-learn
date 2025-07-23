package com.newlearn.playground.mypage.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlearn.playground.event.service.EventService;
import com.newlearn.playground.event.vo.Event;
import com.newlearn.playground.mypage.model.vo.Guestbook;
import com.newlearn.playground.mypage.model.vo.Repository;
import com.newlearn.playground.mypage.service.MypageService;
import com.newlearn.playground.mypage.service.RepositoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {
	private final ServletContext application; 
	private final MypageService mypageService;
	private final EventService eventService;
	private final RepositoryService repoService;
	
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
	public String loadStorage(HttpSession session, Model model) {
		List<Repository> repoList = repoService.getRepoList((int)session.getAttribute("mypageNo"));
		model.addAttribute("repoList", repoList);
		return "mypage/storage";
	}
	
	@PostMapping("/guestbook/hide")
	public String guestbookHide(@RequestParam int guestbookNo, HttpSession session) {
		int result = mypageService.guestbookHide(guestbookNo);
		return "redirect:/mypage/"+session.getAttribute("loginUserNo");
	}
	
	@PostMapping("/guestbook/delete")
	public String guestbookDelete(@RequestParam int guestbookNo, HttpSession session) {
		int result = mypageService.guestbookDelete(guestbookNo);
		return "redirect:/mypage/"+session.getAttribute("loginUserNo");
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
		return "redirect:/mypage/"+mypageNo;
	}
	
	// 개인일정 수정&생성 모달창에 이벤트 객체 바인딩해서 뷰페이지에 띄우기
	@GetMapping("/modal/{dmlType}")
	public String getModal(@PathVariable("dmlType") String dmlType, @RequestParam int eventNo, Model model) {
		Event event = new Event();
		if (dmlType.equals("edit")) {
			event = eventService.findByNo(eventNo);
		}
		model.addAttribute("event", event);
		return "mypage/eventForm";
	}
	
	// 날짜 형식 변환
	@InitBinder
	public void formatDatetime(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	// 내 저장소 div에서 검색기능
	@GetMapping("/storage/search")
	public String searchFile() {
		
		return "mypage/fileList";
	}
}
