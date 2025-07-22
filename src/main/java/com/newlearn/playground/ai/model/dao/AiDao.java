package com.newlearn.playground.ai.model.dao;

import java.util.List;
import java.util.Map;

import com.newlearn.playground.ai.model.vo.Ai_temp;
import com.newlearn.playground.ai.model.vo.AiChatSession;

public interface AiDao {

	List<Ai_temp> getAiList();

	int checkUsedBefore(Map<String, Object> dbParamMap);

	List<AiChatSession> getAiChatSessionsList(int userNo);

}
