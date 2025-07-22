package com.newlearn.playground.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.newlearn.playground.chat.model.service.ChattingRoomListService;
import com.newlearn.playground.chat.model.vo.ChattingRoom;
import com.newlearn.playground.chat.model.vo.Friend;
import com.newlearn.playground.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chat")
public class ChattingRoomListController {
	@Autowired
	private ChattingRoomListService crs;
	@Autowired
	private List<Friend> friendList;
	private List<ChattingRoom> chattingRoomList;
	
	@GetMapping
	public String selectFriendChattingRoomList( // 친구 목록, 채팅방 목록 조회 메서드
			Authentication auth,
			Model model) {
		// 로그인한 회원의 친구, 친구가 속한 채팅방을 조회해야하기 위한 로직
		Member member = (Member)auth.getPrincipal();
		
		// 친구 목록 조회 후 리스트에 담기 
		friendList = crs.selectFriendList();
		model.addAttribute("friendList",friendList);
		
		// 채팅방 목록 조회 후 리스트에 담기
		chattingRoomList = crs.selectChattingRoomList();
		model.addAttribute("chattingRoomList",chattingRoomList);
		
		return "chat/chatRoomList";
	}
	
	@PostMapping("/createRoom")
	public String createRoom( // 채팅방 생성 메서드
			ChattingRoom chattingRoom,
			RedirectAttributes ra,
			Authentication auth) {
		// 로그인한 회원이 방장이 되어 채팅방을 만들게 할 로직
		Member member = (Member)auth.getPrincipal();
		
		// 채팅방을 만들 때 사용자가 입력해야 할 항목
		ChattingRoom room = new ChattingRoom();
		room.setCreaterName(chattingRoom.getCreaterName()); // 개설자
		room.setCreaterProfileImg(chattingRoom.getCreaterProfileImg()); // 개설자 프로필사진
		room.setRoomTitle(chattingRoom.getRoomTitle()); // 방 제목
		room.setJoinnerCount(chattingRoom.getJoinnerCount()); // 참여자 수
		room.setRoomPublic(chattingRoom.getRoomPublic()); // 공개여부
		room.setRoomPassworld(chattingRoom.getRoomPassworld()); // 비밀번호
		room.setRoomAlarm(chattingRoom.getRoomAlarm()); // 알림여부
		
		int result = crs.createRoom(room);
		if(result == 0) {
			// 채팅방 생성 실패
			ra.addFlashAttribute("alertMsg","채팅방 생성 실패");
		}else {
			// 채팅방 생성 성공
			ra.addFlashAttribute("alertMsg","채팅방 생성 성공");
			chattingRoomList.add(room);
		}
		
		return "redirect:/chat/chatRoomList";
	}
	
	@PostMapping("/friendProfile")
	public String friendProfile(
			Friend friend,
			Model model) {
		crs.friendProfile();
		
		return "redirect:/chat/chatRoomList";
	}
}