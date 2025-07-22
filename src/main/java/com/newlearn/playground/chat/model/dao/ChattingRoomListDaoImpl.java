package com.newlearn.playground.chat.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlearn.playground.chat.model.vo.ChattingRoom;
import com.newlearn.playground.chat.model.vo.Friend;

@Repository
public class ChattingRoomListDaoImpl implements ChattingRoomListDao{
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public List<Friend> selectFriendList() {
		// 친구 목록 조회
		return session.selectList("chat.selectFriendList");
	}
	
	@Override
	public List<ChattingRoom> selectChattingRoomList() {
		// 채팅방 목록 조회
		return session.selectList("chat.selectChattingRoomList");
	}

	@Override
	public int createRoom(ChattingRoom room) {
		// 채팅방 생성
		return session.insert("chat.createRoom");
	}
	
}
