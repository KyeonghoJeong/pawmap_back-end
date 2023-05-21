package com.pawmap.facility.dto;

import com.pawmap.facility.entity.FacilityEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListDto {
	
	private Long facilityId;
	private String cat;
	private String facilityName;
	private String basicInfo;
	private String roadAddr;
	private String landAddr;
	private String phoneNum;
	private Double lat;
	private Double lng;
	
	public ListDto(FacilityEntity facilityEntity) {
		this.facilityId = facilityEntity.getFacilityId();
		this.cat = facilityEntity.getCat();
		this.facilityName = facilityEntity.getFacilityName();
		this.basicInfo = facilityEntity.getBasicInfo();
		this.roadAddr = facilityEntity.getRoadAddr();
		this.landAddr = facilityEntity.getLandAddr();
		this.phoneNum = facilityEntity.getPhoneNum();
		this.lat = facilityEntity.getLat();
		this.lng = facilityEntity.getLng();
	}
	
}
