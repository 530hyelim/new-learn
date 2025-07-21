package com.newlearn.playground.chat.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlearn.playground.chat.model.vo.ChattingRoom;

@Repository
public class ChattingRoomListDaoImpl implements ChattingRoomListDao{
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public List<ChattingRoom> selectChattingRoomList() {
		return session.selectList("chat.selectChattingRoomList");
	}
}
