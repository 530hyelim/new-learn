package com.newlearn.playground.member.model.service;

public interface MemberService {

	
	// 아이디 중복 체크
    int idCheck(String checkId);
    
    // 이메일 인증 코드(이메일 보낼 계정 관련해서 나중에 한번 물어보기)
    String sendEmail(String email);

}
