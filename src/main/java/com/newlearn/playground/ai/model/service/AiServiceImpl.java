package com.newlearn.playground.ai.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.newlearn.playground.ai.model.dao.AiDao;
import com.newlearn.playground.ai.model.vo.Ai;
import com.newlearn.playground.ai.model.vo.AiChatSession;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AiServiceImpl implements AiService {
	private final AiDao aiDao;

	@Override
	public List<Ai> getAiList() {
		return aiDao.getAiList();
	}

	@Override
	public int checkUsedBefore(Map<String, Object> dbParamMap) {
		return aiDao.checkUsedBefore(dbParamMap);
	}

	@Override
	public List<AiChatSession> getAiChatSessionsList(int userNo) {
		return aiDao.getAiChatSessionsList(userNo);
	}

}
