package com.newlearn.playground.mypage.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	@Autowired
	private ServletContext application; 
	
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
	
	@GetMapping("/{type}")
	public String loadGuestbook(@PathVariable("type") String type) {
		return "mypage/"+type;
	}
}
