package com.pawmap.facility.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDto {

	private Long facilityId;
	private String facilityName;
	private String lat;
	private String lng;
	
}