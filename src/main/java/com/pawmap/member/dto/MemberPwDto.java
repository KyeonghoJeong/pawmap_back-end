package com.pawmap.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// ��й�ȣ ������ ���� ���� ��й�ȣ, ���ο� ��й�ȣ�� ������ dto Ŭ����

@Getter
@Setter
@AllArgsConstructor
public class MemberPwDto {

	private String pw;
	private String newPw;
	
	public MemberPwDto() {
		
	}
	
}
