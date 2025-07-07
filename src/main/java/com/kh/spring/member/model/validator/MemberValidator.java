package com.kh.spring.member.model.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kh.spring.member.model.vo.Member;

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
		// 유효성 검사 메서드 (위 메서드가 true라면 실행)
		System.out.println("validate method");
		Member member = (Member)target;
		
		// 유효성 검사 로직
		if (member.getUserId() != null) {
			if (member.getUserId().length() < 4 || member.getUserId().length() > 20) {
				System.out.println("userid length error");
				errors.rejectValue("userId", "length", "아이디는 4~20자 이내여야 합니다.");
			}
			if (!member.getUserId().matches("^[a-zA-Z0-9_]+$")) {
				errors.rejectValue("userId", "pattern", "아이디는 영문, 숫자, _만 사용 가능합니다.");
			}
		}
	}

}
