package com.pawmap.map.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// sigungu ��ƼƼ�� ���� ������ ����

@Getter
@Setter
@AllArgsConstructor
public class SigunguDto {
	
	private Long sigunguId;
	private Long sidoId;
	private String sigunguName;

	public SigunguDto() {
		
	}
}