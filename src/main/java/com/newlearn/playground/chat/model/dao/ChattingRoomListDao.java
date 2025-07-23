package com.newlearn.playground.chat.model.dao;

import java.util.List;

import com.newlearn.playground.chat.model.vo.ChattingRoom;
import com.newlearn.playground.chat.model.vo.Friend;
import com.newlearn.playground.chat.model.vo.FriendDTO;

public interface ChattingRoomListDao {

	List<FriendDTO> selectFriendList(int userNo);
	
	List<ChattingRoom> selectChattingRoomList(int userNo);

	int createRoom(ChattingRoom roomInfo);

	List<Friend> friendProfile(int friendNo);

}
