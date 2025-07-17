package com.newlearn.playground.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")   
public class MemberController {

	
	@GetMapping("/enroll")
	public String showAgreePage() {
		return "member/agree";
	}
	
	
	@GetMapping("/enrollForm")
	public String showEnrollPage() {
	
		return "member/enroll";
	}

}




















