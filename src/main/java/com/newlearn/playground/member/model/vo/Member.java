package com.newlearn.playground.member.model.vo;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member implements UserDetails {
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String phone;
	private Date enrollDate;
    private String status;
    private String ssn;    // DB의 컬럼과 매칭할 필드들
    private String userRole;
    
    public String getUserName() {
    	return this.userName;
    }
    
    // UserDetails 구현 메소드들
    
    // 사용자 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	return Collections.singletonList(new SimpleGrantedAuthority(this.userRole));
    }
    
    // 사용자 아이디 반환
    @Override
    public String getUsername() {
    	return this.userId;
    }
    
    // 사용자 비밀번호 반환
    @Override
    public String getPassword() {
    	return this.userPwd;
    }
    
    // 계정만료X
    @Override
    public boolean isAccountNonExpired() {
    	return true;
    }
    
    // 잠금X
    @Override
    public boolean isAccountNonLocked() {
    	return true;
    }
    
    // 비번만료X
    @Override
    public boolean isCredentialsNonExpired() {
    	return true;
    }
    
    // 계정 비활성화X
    @Override
    public boolean isEnabled() {
    	return "Y".equals(this.status);
    }
    
      
}
