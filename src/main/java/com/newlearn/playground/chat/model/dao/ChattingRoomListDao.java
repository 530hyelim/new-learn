package com.newlearn.playground.chat.model.dao;

import java.util.List;

import com.newlearn.playground.chat.model.vo.ChattingRoom;
import com.newlearn.playground.chat.model.vo.Friend;

public interface ChattingRoomListDao {

	List<Friend> selectFriendList();
	
	List<ChattingRoom> selectChattingRoomList();

	int createRoom(ChattingRoom room);

}
