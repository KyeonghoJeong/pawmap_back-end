package com.pawmap.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 로그인, Access Token 재발급 시 회원정보 리턴 DTO

@Getter
@Setter
@AllArgsConstructor
public class AuthDto {

	private String role;
	private String accessToken;
	private String refreshToken;
	
	public AuthDto() {
		
	}
	
}
