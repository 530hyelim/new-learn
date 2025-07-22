package com.newlearn.playground.ai.model.service;

import java.util.List;
import java.util.Map;

import com.newlearn.playground.ai.model.vo.AI;
import com.newlearn.playground.ai.model.vo.AiChatSession;

public interface AiService {

	List<AI> getAiList();

	int checkUsedBefore(Map<String, Object> dbParamMap);

	List<AiChatSession> getAiChatSessionsList(int userNo);

}
