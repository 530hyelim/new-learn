package com.newlearn.playground.chat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.newlearn.playground.chat.model.service.ChattingRoomListService;
import com.newlearn.playground.chat.model.vo.ChattingRoom;
import com.newlearn.playground.chat.model.vo.ChattingRoomDTO;
import com.newlearn.playground.chat.model.vo.FriendDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chat")
public class ChattingRoomListController {
	@Autowired
	private ChattingRoomListService crs;
	
	// 메인화면
	@GetMapping("/main")
	public String chatMain(
			Authentication auth,
			Model model) {
		// 로그인 기능 추가 후 주석 해제.
		//Member member = (Member)auth.getPrincipal();
		//int userNo = member.getUserNo();
		int userNo = 3;
		
		// 친구 목록 조회 후 리스트에 담기
		List<FriendDTO> friendList = crs.selectFriendList(userNo);
		model.addAttribute("friendList",friendList);
//		log.debug("friendList : {}",friendList);
		
		// 채팅방 목록 조회 후 리스트에 담기
		List<ChattingRoom> chattingRoomList = crs.selectChattingRoomList(userNo);
		model.addAttribute("chattingRoomList",chattingRoomList);
//		log.debug("chattingRoomList : {}",chattingRoomList);
		
		return "chat/chatMain";
	}
	
	/* 채팅방 생성
	 * 
	 * 현재 유저가 방장이 되어
	 * 모달창에 입력한 정보대와 추가한 인원대로 새로운 채팅방을 생성
	 * 생성된 채팅방을 채팅방 리스트에 추가
	 */
	@ResponseBody
	@PostMapping("/createRoom")
	public Map<String,Object> createRoom(
			ChattingRoomDTO room,
			@RequestParam List<Integer> selectFriendList,
//			@RequestParam(required = false) String searchType,
			Authentication auth
			) {
		Map<String,Object> response = new HashMap<>();
		
		// 로그인 기능 추가 후 주석 해제.
//		 Member member = (Member)auth.getPrincipal();
//		 int userNo = member.getUserNo();
		int userNo = 3;
//		System.out.println(userNo);
//		System.out.println(searchType);
//		
//		// 채팅방을 만들 때 사용자가 입력해야 할 항목
		Map<String, Object> createRoomInf = new HashMap<>();
		createRoomInf.put("userNo", userNo);
		createRoomInf.put("chatTitle", room.getChatTitle());
		createRoomInf.put("chatPublic", room.getChatPublic());
		createRoomInf.put("chatPw", room.getChatPw());
		createRoomInf.put("selectFriendList", selectFriendList);
		
		int result = crs.createRoom(createRoomInf);
		if(result > 0) {
			response.put("success",true);
			response.put("message", "채팅방 생성 성공");
		}else {
			response.put("success",false);
			response.put("message", "채팅방 생성 실패");
		}
		
		return response;
	}
	
//	/* 친구 프로필정보
//	 * 
//	 * 친구목록에서 선택한 친구의
//	 * 이름, 프로필사진, 상태메세지, 로그인 유저와 겹치는 클래스 수와 클래스명 조회 후 포워딩
//	 */
//	@PostMapping("/friendProfile")
//	public String friendProfile(
//			Friend friend,
//			Model model) {
//		int friendNo = friend.getUserNo();
//		List<Friend> friendInfo = crs.friendProfile(friendNo);
//		model.addAttribute("friendInfo",friendInfo);
//		
//		return "redirect:/chat/chatMain";
//	}
}