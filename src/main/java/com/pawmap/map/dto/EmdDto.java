package com.pawmap.map.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// emd ��ƼƼ�� ���� ������ ����

@Getter
@Setter
@AllArgsConstructor
public class EmdDto {
	
	private Long emdId;
	private Long sigunguId;
	private String emdName;
	
	public EmdDto() {
		
	}

}