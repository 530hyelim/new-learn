package com.newlearn.playground.chat.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newlearn.playground.chat.model.dao.ChatDao;
import com.newlearn.playground.chat.model.vo.ChatMessage;
import com.newlearn.playground.chat.model.vo.ChatRoom;
import com.newlearn.playground.chat.model.vo.ChatRoomJoin;
import com.newlearn.playground.common.Utils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService{@Override
	public List<ChatRoom> selectChatRoomList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int openChatRoom(ChatRoom room) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ChatMessage> joinChatRoom(ChatRoomJoin join) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertMessage(ChatMessage chatMessage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void exitChatRoom(ChatMessage message) {
		// TODO Auto-generated method stub
		
	}		

}
