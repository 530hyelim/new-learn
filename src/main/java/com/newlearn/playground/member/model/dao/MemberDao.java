package com.newlearn.playground.member.model.dao;

import java.util.HashMap;

import com.newlearn.playground.member.model.vo.Member;

public interface MemberDao {

	Member loginUser(String userId);

	int insertMember(Member m);

	int updateMember(Member m);

	int idCheck(String userId);

	void updateMemberChagePwd();

	Member loginMember(Member m);

	HashMap<String, Object> selectOne(String userId);

	void insertAuthority(Member m);
	
	String findId(String userName, String ssn);
	
	int updatePassword(String userId, String newPassword);
	
	/* @param userName 이름
	 * @param ssn 주민번호
	 * @return 0 또는 1로 리턴
	 * */
	int checkNameAndSsn(String userName, String ssn);
	

}
