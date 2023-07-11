package com.pawmap.map.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// �ü��� ��ġ ����(����, �浵)�� ������ �ִ� dto

@Getter
@Setter
@AllArgsConstructor
public class FacilityLocationDto {

	private Long facilityId;
	private String facilityName;
	private String lat;
	private String lng;
	
	public FacilityLocationDto() {
		
	}
	
}