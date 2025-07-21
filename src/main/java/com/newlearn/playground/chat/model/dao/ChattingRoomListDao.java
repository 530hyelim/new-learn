package com.newlearn.playground.chat.model.dao;

import java.util.List;

import com.newlearn.playground.chat.model.vo.ChattingRoom;

public interface ChattingRoomListDao {

	List<ChattingRoom> selectChattingRoomList();

}
