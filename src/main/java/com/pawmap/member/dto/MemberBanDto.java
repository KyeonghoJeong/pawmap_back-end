package com.pawmap.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// ȸ�� ���� ��û�� �޴� �� ���

@Getter
@Setter
@AllArgsConstructor
public class MemberBanDto {

	private String memberId;
	private String order;
	
	public MemberBanDto(){
		
	}
	
}
