package com.pawmap.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacilityDto {
	
	private Long facility_id;
	private String facility_name;
	private String basic_info;
	private String road_addr;
	private double lat;
	private double lng;
	
}
