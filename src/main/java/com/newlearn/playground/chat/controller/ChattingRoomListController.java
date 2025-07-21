package com.newlearn.playground.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlearn.playground.chat.model.service.ChattingRoomListService;
import com.newlearn.playground.chat.model.vo.ChattingRoom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chat")
public class ChattingRoomListController {
	@Autowired
	private ChattingRoomListService crs;
	
	@GetMapping
	public String selectChattingRoomList(Model model) {
		/*
		 * 채팅방 목록 조회 후 리스트에 담기
		 */
		List<ChattingRoom> list = crs.selectChattingRoomList();
		
		model.addAttribute("list",list);
		
		return "chat/chatRoomList";
	}
	
}