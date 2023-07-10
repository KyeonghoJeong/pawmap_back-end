package com.pawmap.member.dto;

import lombok.Getter;
import lombok.Setter;

// 회원 차단 요청을 받는 데 사용

@Getter
@Setter
public class MemberBanDto {

	private String memberId;
	private String order;
	
	public MemberBanDto(){
		
	}
	
}
