package com.newlearn.playground.chat.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlearn.playground.chat.model.dao.ChattingRoomListDao;
import com.newlearn.playground.chat.model.vo.ChattingRoom;
import com.newlearn.playground.chat.model.vo.Friend;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChattingRoomListServiceImpl implements ChattingRoomListService{
	@Autowired
	private ChattingRoomListDao crd;
	
	@Override
	public List<Friend> selectFriendList() {
		// 친구 목록 조회
		return crd.selectFriendList();
	}
	
	@Override
	public List<ChattingRoom> selectChattingRoomList() {
		// 채팅방 목록 조회
		return crd.selectChattingRoomList();
	}

	@Override
	public int createRoom(ChattingRoom room) {
		// 채팅방 생성
		return crd.createRoom(room);
	}
	
}
