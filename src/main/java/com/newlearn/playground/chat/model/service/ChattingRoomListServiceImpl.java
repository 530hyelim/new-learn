package com.newlearn.playground.chat.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlearn.playground.chat.model.dao.ChattingRoomListDao;
import com.newlearn.playground.chat.model.vo.ChattingRoom;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChattingRoomListServiceImpl implements ChattingRoomListService{
	@Autowired
	private ChattingRoomListDao crd;

	@Override
	public List<ChattingRoom> selectChattingRoomList() {
		return crd.selectChattingRoomList();
	}
	
}
