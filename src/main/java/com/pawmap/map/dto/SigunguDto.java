package com.pawmap.map.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// sigungu 엔티티와 같은 데이터 구조

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