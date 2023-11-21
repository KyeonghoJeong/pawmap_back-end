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

// ����� ���� jwt accessToken ��ū�� ��ȿ���� �˻��ϴ� Ŭ����

public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private final JwtTokenProvider jwtTokenProvider;
	
	public JwtAuthenticationFilter() {
		JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
		
		this.jwtTokenProvider = jwtTokenProvider;
	}

	// resolveToken���� ���� ���� accessToken�� ��ȿ�� �˻�
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accessToken = resolveToken(request);
		
		// accessToken�� null�� �ƴϰ� jwtTokenProvider�� ��ȿ�� �˻� true�� ���
		if(accessToken != null && jwtTokenProvider.validateToken(accessToken)) {
			// ȸ�� ���̵�, ��й�ȣ, ���� ������� UserDetails ��ü�� ������ authentication ��ü �ޱ�
			Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
			
			SecurityContextHolder.getContext().setAuthentication(authentication); // SecurityContextHolder�� �������� authentication�� SecurityContext�� ����
		}
		
		filterChain.doFilter(request, response);
	}

	// ��� �̸� Authorization���� accessToken�� �޾Ƽ� accessToken�� �߶� ����
	private String resolveToken(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String bearerToken = request.getHeader("Authorization");
		
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7); // accessToken �� 7�ڸ� "Bearer " ����
		}
		
		return null;
	}

}
