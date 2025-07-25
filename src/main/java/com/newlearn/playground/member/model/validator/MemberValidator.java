package com.newlearn.playground.member.model.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.newlearn.playground.member.model.vo.Member;

public class MemberValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// 유효성 검사를 수행할 클래스를 지정하는 메서드.
		System.out.println("supports method");
		boolean result = Member.class.isAssignableFrom(clazz);
		
		System.out.println(result);
		
		return result;
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Member member = (Member) target;
		String userId = member.getUserId();
		
		// 아이디 길이 검사
		if(userId == null || userId.length() < 7 || userId.length() > 15) {
			errors.rejectValue("userId", "length", "아이디는 7~15자 사이로 입력해주세요.");
		}
		
		// 아이디 형식 검사
		if(userId != null && !userId.matches("^[a-zA-Z0-9]+$")) {
			errors.rejectValue("userId", "pattern", "아이디는 영문자와 숫자로만 구성되어야 합니다.");
		}
	}
	
	
}
