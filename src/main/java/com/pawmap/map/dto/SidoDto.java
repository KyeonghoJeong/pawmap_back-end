package com.pawmap.map.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// sido 엔티티와 같은 데이터 구조

@Getter
@Setter
@AllArgsConstructor
public class SidoDto {

	private Long sidoId;
	private String sidoName;

	public SidoDto() {
		
	}
}
