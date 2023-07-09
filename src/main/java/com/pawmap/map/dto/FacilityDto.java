package com.pawmap.map.dto;

import lombok.Getter;
import lombok.Setter;

// 시설 엔티티와 데이터 구조가 동일

@Getter
@Setter
public class FacilityDto {

	private Long facilityId;
	private String sido;
	private String sigungu;
	private String emd;
	private String cat;
	private String facilityName;
	private String basicInfo;
	private String roadAddr;
	private String landAddr;
	private String phoneNum;
	private String website;
	private String closedDay;
	private String businessHr;
	private String parkingAvail;
	private String admissionFee;
	private String petFee;
	private String petExclusive;
	private String petSize;
	private String petRestrictions;
	private String indoorAvail;
	private String outdoorAvail;
	private Double lat;
	private Double lng;
	
}
