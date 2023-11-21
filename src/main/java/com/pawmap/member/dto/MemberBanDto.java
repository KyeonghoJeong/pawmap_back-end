package com.pawmap.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 관리자가 회원 차단 요청할 때 사용하는 DTO

@Getter
@Setter
@AllArgsConstructor
public class MemberBanDto {

	private String memberId;
	private String order;
	
	public MemberBanDto(){
		
	}
	
}
