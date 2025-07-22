package com.newlearn.playground.chat.model.service;

import java.util.List;

import com.newlearn.playground.chat.model.vo.ChattingRoom;
import com.newlearn.playground.chat.model.vo.Friend;

public interface ChattingRoomListService {

	List<Friend> selectFriendList(int userNo);
	
	List<ChattingRoom> selectChattingRoomList(int userNo);

	int createRoom(ChattingRoom roomInfo);

	List<Friend> friendProfile(int friendNo);

}
