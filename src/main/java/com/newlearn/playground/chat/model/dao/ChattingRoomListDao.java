package com.newlearn.playground.chat.model.dao;

import java.util.List;

import com.newlearn.playground.chat.model.vo.ChattingRoom;
import com.newlearn.playground.chat.model.vo.Friend;

public interface ChattingRoomListDao {

	List<Friend> selectFriendList(int userNo);
	
	List<ChattingRoom> selectChattingRoomList(int userNo);

	int createRoom(ChattingRoom roomInfo);

	List<Friend> friendProfile(int friendNo);

}
