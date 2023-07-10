package com.pawmap.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 게시글 작성자와 로그인 회원 식별에 사용

@Getter
@Setter
@AllArgsConstructor
public class MemberIdentificationDto {

	private String memberId;
	private boolean isItsMember;
	
	public MemberIdentificationDto() {
		
	}
	
}
