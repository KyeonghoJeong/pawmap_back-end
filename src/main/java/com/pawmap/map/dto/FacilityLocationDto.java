package com.pawmap.map.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 시설의 위치 정보(위도, 경도)를 가지고 있는 dto

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