package com.pawmap.map.dto;

import lombok.Getter;
import lombok.Setter;

// �ü��� ��ġ ����(����, �浵)�� ������ �ִ� dto

@Getter
@Setter
public class FacilityLocationDto {

	private Long facilityId;
	private String facilityName;
	private String lat;
	private String lng;
	
}