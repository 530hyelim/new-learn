package com.newlearn.playground.chat.controller;

import java.util.List;
import java.util.Map;

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
import com.newlearn.playground.chat.model.vo.FriendDTO;
import com.newlearn.playground.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chat")
public class ChattingRoomListController {
	@Autowired
	private ChattingRoomListService crs;
	
	/* 메인화면
	 * 
	 * 현재 유저의 
	 * 친구 목록 조회 후 포워딩
	 * 채팅방 목록 조회 후 포워딩
	 * 
	 */
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
		log.debug("chattingRoomList : {}",chattingRoomList);
		
		return "chat/chatMain";
	}
	
//	/* 채팅방 생성
//	 * 
//	 * 현재 유저가 방장이 되어
//	 * 모달창에 입력한 정보대와 추가한 인원대로 새로운 채팅방을 생성
//	 * 생성된 채팅방을 채팅방 리스트에 추가
//	 */
//	@PostMapping("/createRoom")
//	public String createRoom(
//			ChattingRoom room,
//			RedirectAttributes ra,
//			Authentication auth) {
//		// 로그인한 회원이 방장이 되어 채팅방을 만들게 할 로직
//		Member member = (Member)auth.getPrincipal();
//		int userNo = member.getUserNo();
//		
//		// 채팅방을 만들 때 사용자가 입력해야 할 항목
//		ChattingRoom roomInfo = new ChattingRoom();
//		roomInfo.setCreaterName(room.getCreaterName()); // 개설자
//		roomInfo.setCreaterProfileImg(room.getCreaterProfileImg()); // 개설자 프로필사진
//		roomInfo.setRoomTitle(room.getRoomTitle()); // 방 제목
//		roomInfo.setJoinnerCount(room.getJoinnerCount()); // 참여자 수
//		roomInfo.setRoomPublic(room.getRoomPublic()); // 공개여부
//		roomInfo.setRoomPassworld(room.getRoomPassworld()); // 비밀번호
//		roomInfo.setRoomAlarm(room.getRoomAlarm()); // 알림여부
//		
//		int result = crs.createRoom(roomInfo);
//		if(result == 0) {
//			// 채팅방 생성 실패
//			ra.addFlashAttribute("alertMsg","채팅방 생성 실패");
//		}else {
//			// 채팅방 생성 성공
//			ra.addFlashAttribute("alertMsg","채팅방 생성 성공");
//			chattingRoomList.add(roomInfo);
//		}
//		
//		return "redirect:/chat/chatMain";
//	}
//	
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