package com.newlearn.playground.chat.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	@Override
	public int createRoom(ChattingRoom roomInfo) {
		return crd.createRoom(roomInfo);
	}

	@Override
	public List<Friend> friendProfile(int friendNo) {
		return crd.friendProfile(friendNo);
	}
	
}
