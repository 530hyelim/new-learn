package com.newlearn.playground.board.model.dao;

import java.util.List;

import com.newlearn.playground.board.model.vo.Reply;

public interface ReplyDao {

	List<Reply> selectReplyList(int boardNo);

	int insertReply(Reply r);

	int deleteReply(int replyNo);

	int updateReply(Reply r);

}
