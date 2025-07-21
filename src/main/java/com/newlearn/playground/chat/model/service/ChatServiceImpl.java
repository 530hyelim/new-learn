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
public class ChatServiceImpl implements ChatService{
	@Autowired
	private ChatDao dao;

	@Override
	public List<ChatRoom> selectChatRoomList() {
		return dao.selectChatRoomList();
	}

	@Override
	public int openChatRoom(ChatRoom room) {
		room.setTitle(Utils.XSSHandling(room.getTitle()));
		return dao.openChatRoom(room);
	}

	@Override
	public List<ChatMessage> joinChatRoom(ChatRoomJoin join) {
		// 현재 회원이 해당 채팅방에 참여하고 있는지 확인
		List<ChatMessage> list = null;
		int result = dao.joinCheck(join);// 참여하고 있다면 1, 없다면 0

		if(result == 0) {
			// 참여자 정보를 Chat_room_join에 Insert
			result = dao.joinChatRoom(join);
		}
		
		// insert성공시 list를 반환, 실패시 null반환
		if(result > 0) {
//			list = dao.selctChatMessage(join.getChatRoomNo());
		}
		
		return list;
	}

	@Override
	public int insertMessage(ChatMessage chatMessage) {
		return dao.insertMessage(chatMessage);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void exitChatRoom(ChatMessage join) {
		// 채팅방 나가기 처리
		int result = dao.exitChatRoom(join);
		
		if(result == 0) {
			throw new RuntimeException("채팅방 나가기 처리 에러");
		}
		
		// 마지막으로 나간인원이 본인이라면 채팅방 삭제처리
		
		// 현재 채팅방 인원수 체크
		int cnt = dao.countChatRoomMember(join);
		
		// 채팅방에 남은 사람이 없는 경우 채팅방 상태 변경
		if(cnt == 0) {
			result = dao.closeChatRoom(join);
			
			if(result == 0) {
				throw new RuntimeException("채팅방 삭제 오류");
			}
		}
		
		
	}
	
	
	
	
	
	

}