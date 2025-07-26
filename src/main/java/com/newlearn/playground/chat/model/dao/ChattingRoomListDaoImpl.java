package com.newlearn.playground.chat.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlearn.playground.chat.model.vo.ChattingRoom;
import com.newlearn.playground.chat.model.vo.Friend;
import com.newlearn.playground.chat.model.vo.FriendDTO;

@Repository
public class ChattingRoomListDaoImpl implements ChattingRoomListDao{
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public List<FriendDTO> selectFriendList(int userNo) {
		// 친구 목록 조회
		return session.selectList("chat.selectFriendList",userNo);
	}
	
	@Override
	public List<ChattingRoom> selectChattingRoomList(int userNo) {
		// 채팅방 목록 조회
		return session.selectList("chat.selectChattingRoomList",userNo);
	}

	@Override
	public int createRoom(Map<String, Object> createRoomInf) {
		return session.insert("chat.createRoom",createRoomInf);
	}
	
	@Override
	public int insertUserJoin(Map<String, Object> createRoomInf) {
		return session.insert("chat.insertUserJoin");
	}

	@Override
	public int insertFriendJoin(Map<String, Object> createRoomInf) {
		return session.insert("chat.insertFriendJoin");
	}

	@Override
	public List<Friend> friendProfile(int friendNo) {
		// 친구 프로필 조회
		return session.selectList("chat.friendProfile",friendNo);
	}
	
}
