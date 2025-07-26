package com.newlearn.playground.chat.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newlearn.playground.chat.model.dao.ChattingRoomListDao;
import com.newlearn.playground.chat.model.vo.ChattingRoom;
import com.newlearn.playground.chat.model.vo.Friend;
import com.newlearn.playground.chat.model.vo.FriendDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChattingRoomListServiceImpl implements ChattingRoomListService{
	@Autowired
	private ChattingRoomListDao crd;
	
	@Override
	public List<FriendDTO> selectFriendList(int userNo) {
		// 친구 목록 조회
		return crd.selectFriendList(userNo);
	}
	
	@Override
	public List<ChattingRoom> selectChattingRoomList(int userNo) {
		// 채팅방 목록 조회
		return crd.selectChattingRoomList(userNo);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int createRoom(Map<String, Object> createRoomInf) {
		int result = 0;
		// CHAT_ROOM 테이블에 방만들기 입력값 추가
		result += crd.createRoom(createRoomInf);
		
		// CHAT_JOIN에 방장 정보 추가
		result += crd.insertUserJoin(createRoomInf);
		
		// CHAT_JOIN에 선택한 친구 정보 추가
		List<Integer> selectFriendList = (List<Integer>)createRoomInf.get("selectFriendList");
		if(selectFriendList != null && !selectFriendList.isEmpty()) {
			result += crd.insertFriendJoin(createRoomInf);
		}
		
		return result;
	}

	@Override
	public List<Friend> friendProfile(int friendNo) {
		return crd.friendProfile(friendNo);
	}
	
}
