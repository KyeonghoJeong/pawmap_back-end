package com.pawmap.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 비밀번호 변경을 위해 기존 비밀번호, 새로운 비밀번호를 저장할 dto 클래스

@Getter
@Setter
@AllArgsConstructor
public class MemberPwDto {

	private String pw;
	private String newPw;
	
	public MemberPwDto() {
		
	}
	
}
