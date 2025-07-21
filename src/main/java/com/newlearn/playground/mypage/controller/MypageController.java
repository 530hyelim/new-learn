package com.newlearn.playground.mypage.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlearn.playground.mypage.model.vo.Guestbook;
import com.newlearn.playground.mypage.service.MypageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {
	private final ServletContext application; 
	private final MypageService mypageService;
	
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
	public String loadCalendar(Model model) {
		
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
