package com.newlearn.playground.ai.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.newlearn.playground.ai.model.vo.Ai_temp;
import com.newlearn.playground.ai.model.vo.AiChatSession;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AiDaoImpl implements AiDao {
	private final SqlSessionTemplate session;

	@Override
	public List<Ai_temp> getAiList() {
		return session.selectList("ai.getAiList", "modelNo");
	}

	@Override
	public int checkUsedBefore(Map<String, Object> dbParamMap) {
		return session.selectOne("ai.checkUsedBefore", dbParamMap);
	}

	@Override
	public List<AiChatSession> getAiChatSessionsList(int userNo) {
		return session.selectList("ai.getAiChatSessionsList", userNo);
	}

}
