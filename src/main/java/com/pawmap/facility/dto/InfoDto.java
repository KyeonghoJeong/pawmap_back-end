package com.pawmap.facility.dto;

import com.pawmap.facility.entity.FacilityEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoDto {
	
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
	private String petAvail;
	private String petExclusive;
	private String petSize;
	private String petRestrictions;
	private String indoorAvail;
	private String outdoorAvail;
	private double lat;
	private double lng;
	
	public InfoDto(FacilityEntity facilityEntity) {
		this.facilityId = facilityEntity.getFacilityId();
		this.sido = facilityEntity.getSido();
		this.sigungu = facilityEntity.getSigungu();
		this.emd = facilityEntity.getEmd();
		this.cat = facilityEntity.getCat();
		this.facilityName = facilityEntity.getFacilityName();
		this.basicInfo = facilityEntity.getBasicInfo();
		this.roadAddr = facilityEntity.getRoadAddr();
		this.landAddr = facilityEntity.getLandAddr();
		this.phoneNum = facilityEntity.getPhoneNum();
		this.website = facilityEntity.getWebsite();
		this.closedDay = facilityEntity.getClosedDay();
		this.businessHr = facilityEntity.getBusinessHr();
		this.parkingAvail = facilityEntity.getParkingAvail();
		this.admissionFee = facilityEntity.getAdmissionFee();
		this.petFee = facilityEntity.getPetFee();
		this.petAvail = facilityEntity.getPetAvail();
		this.petExclusive = facilityEntity.getPetExclusive();
		this.petSize = facilityEntity.getPetSize();
		this.petRestrictions = facilityEntity.getPetRestrictions();
		this.indoorAvail = facilityEntity.getIndoorAvail();
		this.outdoorAvail = facilityEntity.getOutdoorAvail();
		this.lat = facilityEntity.getLat();
		this.lng = facilityEntity.getLng();
	}
	
}
