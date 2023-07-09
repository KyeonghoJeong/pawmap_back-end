package com.pawmap.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

// AuthenticationProvider 커스텀 구현 클래스

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 회원의 아이디를 사용하여 해당하는 회원정보를 확인
	// 해당 회원의 비밀번호와 입력한 비밀번호가 일치하는 지 확인
	// 일치 시 인증된 Authentication 객체 생성 후 리턴
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		// 전달 받은 authentication에서 아이디와 비밀번호 추출
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
	    try {
	    	UserDetails userDetails = userDetailsService.loadUserByUsername(username); // 해당 아이디의 엔티티(회원정보) 가져오기
	    	
	    	// 프론트에서 사용자가 입력한 비밀번호와 DB 테이블에 등록되어 있는 비밀번호 일치 확인
	    	// 다를 경우 예외 발생
			if(!passwordEncoder.matches(password, userDetails.getPassword())) {
				log.info("password is not matched");
				throw new BadCredentialsException("password is not matched");
			}
			
			// 인증된 회원 Authentication 객체 생성 및 리턴
			// 파라미터 각각 회원 엔티티, 비밀번호 (인증 이후 필요 없으므로 null), 회원 권한
			Authentication authenticatedMember = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
		    return authenticatedMember;
	    } catch (UsernameNotFoundException e) { // userDetailsService에서 발생한 UsernameNotFoundException 처리
	    	log.info("username is not found");
	        throw new BadCredentialsException("username is not found");
	    }
	}

	// 회원 로그인 시 CustomAuthenticationProvider가 실행되고 supports 메소드가 호출됨
	// 매개변수인 authenticaiton 객체를 UsernamePasswordAuthenticationToken 클래스가 지원하는 지 확인 후 true or false 리턴
	// true인 경우 authenticate가 실행 됨
	// false인 경우 authenticate가 실행되지 않고 CustomAuthenticationProvider가 스킵됨
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}