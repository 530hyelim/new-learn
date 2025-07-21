package com.newlearn.playground.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlearn.playground.chat.model.service.ChattingRoomListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chat")
public class ChattingRoomListController {
	@Autowired
	private ChattingRoomListService crs;
	
	@GetMapping
	public String friendCount(@RequestParam String ) {
		
		
		
		return "chat/chatRoomList";
	}
	
}