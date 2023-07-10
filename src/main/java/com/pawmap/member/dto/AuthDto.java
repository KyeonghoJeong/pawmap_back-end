package com.pawmap.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// ȸ�� ���������� �����ϴ� �� ���

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
