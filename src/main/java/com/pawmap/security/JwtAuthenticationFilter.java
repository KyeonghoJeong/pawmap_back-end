package com.pawmap.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

// 헤더로 받은 jwt accessToken 토큰의 유효성을 검사하는 클래스

public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private final JwtTokenProvider jwtTokenProvider;
	
	public JwtAuthenticationFilter() {
		JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
		
		this.jwtTokenProvider = jwtTokenProvider;
	}

	// resolveToken에서 리턴 받은 accessToken의 유효성 검사
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accessToken = resolveToken(request);
		
		// accessToken이 null이 아니고 jwtTokenProvider의 유효성 검사 true인 경우
		if(accessToken != null && jwtTokenProvider.validateToken(accessToken)) {
			// 회원 아이디, 비밀번호, 권한 목록으로 UserDetails 객체로 생성한 authentication 객체 받기
			Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
			
			SecurityContextHolder.getContext().setAuthentication(authentication); // SecurityContextHolder로 인증받은 authentication을 SecurityContext에 설정
		}
		
		filterChain.doFilter(request, response);
	}

	// 헤더 이름 Authorization으로 accessToken을 받아서 accessToken만 잘라 리턴
	private String resolveToken(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String bearerToken = request.getHeader("Authorization");
		
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7); // accessToken 앞 7자리 "Bearer " 삭제
		}
		
		return null;
	}

}
