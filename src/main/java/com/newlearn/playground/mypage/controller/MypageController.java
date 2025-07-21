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
			System.out.println(path);
			File[] files = path.listFiles();
			List<File> fileList = Arrays.asList(files);
			model.addAttribute("fileList", fileList);
			for(File f : fileList) {
				System.out.println(fileList);
			}
		}
		return "mypage/mypage";
	}
}
