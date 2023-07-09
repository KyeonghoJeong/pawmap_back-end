package com.pawmap.map.dto;

import com.pawmap.map.entity.FacilityEntity;

import lombok.Getter;
import lombok.Setter;

// 시설의 일부 정보를 담은 dto
// 지도 페이지에서 화면 왼쪽에 출력

@Getter
@Setter
public class FacilityCardDto {
	
	private Long facilityId;
	private String cat;
	private String facilityName;
	private String basicInfo;
	private String roadAddr;
	private String landAddr;
	private String phoneNum;
	private String businessHr;
	private Double lat;
	private Double lng;
	
	public FacilityCardDto(FacilityEntity facilityEntity) {
		this.facilityId = facilityEntity.getFacilityId();
		this.cat = facilityEntity.getCat();
		this.facilityName = facilityEntity.getFacilityName();
		this.basicInfo = facilityEntity.getBasicInfo();
		this.roadAddr = facilityEntity.getRoadAddr();
		this.landAddr = facilityEntity.getLandAddr();
		this.phoneNum = facilityEntity.getPhoneNum();
		this.businessHr = facilityEntity.getBusinessHr();
		this.lat = facilityEntity.getLat();
		this.lng = facilityEntity.getLng();
	}
	
}
