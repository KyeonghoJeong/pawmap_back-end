package com.pawmap.facility.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailDto {
	
	private Long facilityId;
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

}
