package com.pawmap.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 회원 인증정보를 리턴하는 데 사용

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
