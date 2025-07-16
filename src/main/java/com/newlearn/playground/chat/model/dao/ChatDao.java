package com.newlearn.playground.chat.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlearn.playground.chat.model.vo.ChatMessage;
import com.newlearn.playground.chat.model.vo.ChatRoom;
import com.newlearn.playground.chat.model.vo.ChatRoomJoin;

@Repository
public class ChatDao {
//	@Autowired
//	private SqlSessionTemplate session;
//
//	public List<ChatRoom> selectChatRoomList() {
//		return session.selectList("chat.selectChatRoomList");
//	}
//
//	public int openChatRoom(ChatRoom room) {
//		return session.insert("chat.openChatRoom", room);
//		
//	}
//
//	public int joinCheck(ChatRoomJoin join) {
//		return session.selectOne("chat.joinCheck", join);
//	}
//
//	public int joinChatRoom(ChatRoomJoin join) {
//		return session.insert("chat.joinChatRoom", join);
//	}
//	
//	public List<ChatMessage> selectChatMessage(int chatRoomNo) {
//		return session.selectList("chat.selectChatMessage", chatRoomNo);
//	}
//
//	public int insertMessage(ChatMessage chatMessage) {
//		return session.insert("chat.insertMessage", chatMessage);
//	}
//
//	public int exitChatRoom(ChatMessage message) {
//		return session.delete("chat.exitChatRoom", message);
//	}
//
//	public int countChatRoomMember(ChatMessage message) {
//		return session.selectOne("chat.countChatRoomMember", message);
//	}
//
//	public int closeChatRoom(ChatMessage message) {
//		return session.update("chat.closeChatRoom", message);
//	}


}
