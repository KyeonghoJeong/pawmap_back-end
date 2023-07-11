package com.pawmap.map.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// emd 엔티티와 같은 데이터 구조

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