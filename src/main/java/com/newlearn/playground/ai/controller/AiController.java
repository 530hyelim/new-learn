package com.newlearn.playground.ai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newlearn.playground.ai.model.service.AiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {
	private final AiService aiService;
	
	@GetMapping("/main")
	public String showMain() {
		return "ai/aiMain";
	}
	
	@PostMapping("/main")
	public void getPrompt(
			@RequestParam(value="prompt", required=false) String prompt
			) {
		log.info("{}", prompt);
	}
}
