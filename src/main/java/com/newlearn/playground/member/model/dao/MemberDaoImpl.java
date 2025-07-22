package com.newlearn.playground.member.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.newlearn.playground.member.model.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao{
	
	private final SqlSessionTemplate sqlSession;
	
	public MemberDaoImpl(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Member loginUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMember(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int idCheck(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateMemberChagePwd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Member loginMember(Member m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Object> selectOne(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertAuthority(Member m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String findId(String userName, String ssn) {
		Map<String, String> params = new HashMap<>();
		params.put("userName", userName);
		params.put("ssn", ssn);
		
		return sqlSession.selectOne("memberMapper.findId", params);
	}
	
	@Override
	public int updatePassword(String userId, String newPassword) {
		Map<String, String> params = new HashMap<>();
		params.put("userId", userId);
		params.put("newPassword", newPassword);
		
		return sqlSession.update("memberMapper.updatePassword", params);
	}
	
}
