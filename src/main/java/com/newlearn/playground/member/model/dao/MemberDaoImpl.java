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
	public Member loginMember(Member m) {
		
		return sqlSession.selectOne("memberMapper.loginMember", m);
	}

	@Override
	public int insertMember(Member m) {
		
		return sqlSession.insert("memberMapper.insertMember", m);
	}

	@Override
	public int updateMember(Member m) {

		return sqlSession.update("memberMapper.updateMember", m);
	}

	@Override
	public int idCheck(String userId) {

		return sqlSession.selectOne("memberMapper.idCheck", userId);
	}

	@Override
	public void insertAuthority(Member m) {
		// sqlSession.insert("memberMapper.insertAuthority", m);
		// Authority 관련 테이블 나중에 한번 물어보고 주석해제
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
	
	@Override
	public int checkNameAndSsn(String userName, String ssn) {
		Map<String, String> params = new HashMap<>();
		params.put("userName", userName);
		params.put("ssn", ssn);
		
		return sqlSession.selectOne("memberMapper.checkNameAndSsn", params);
	}
	
	
	@Override
	public String findUserForPasswordReset(String userName, String ssn,
			String email) {
		Map<String, String> params = new HashMap<>();
		params.put("userName", userName);
		params.put("ssn", ssn);
		params.put("email", email);
		return sqlSession.selectOne("memberMapper.findUserForPasswordReset", params);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 미사용중인 메소드
	@Override
	public Member loginUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void updateMemberChagePwd() {
		
	}
	
	@Override
	public HashMap<String, Object> selectOne(String userId) {
		
		return null;
	}
	
	
	
}
