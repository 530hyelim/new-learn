package com.newlearn.playground.member.model.service;

import java.util.HashMap;

import com.newlearn.playground.member.model.vo.Member;

public interface MemberService {
 
    // 이메일 인증 코드(이메일 보낼 계정 관련해서 나중에 한번 물어보기)
    String sendEmail(String email);
	Member loginMember(String userId);
	
	Member loginMember(Member m);

	int insertMember(Member m);

	int updateMember(Member m);

	int idCheck(String userId);

	void updateMemberChagePwd();

	HashMap<String, Object> selectOne(String userId);
	
	String findId(String userName, String ssn);
	
	int updatePassword(String userId, String newPassword);
	
	// 아이디 중복 체크
	  //  int idCheck(String checkId);
	
	String findUserForPasswordReset(String userName, String ssn, String email);

}
