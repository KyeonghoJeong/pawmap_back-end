package com.pawmap.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// �Խñ� �ۼ��ڿ� �α��� ȸ�� �ĺ��� ���

@Getter
@Setter
@AllArgsConstructor
public class MemberIdentificationDto {

	private String memberId;
	private boolean isItsMember;
	
	public MemberIdentificationDto() {
		
	}
	
}
