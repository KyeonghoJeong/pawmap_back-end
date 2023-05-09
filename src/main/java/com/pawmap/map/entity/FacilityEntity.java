package com.pawmap.map.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Table(name = "facility")
@Entity
public class FacilityEntity {
	
	@Id
	@Column(name = "facilityid")
	private Long facilityId;
	
	@Column(name = "sido")
	private String sido;
	
	@Column(name = "sigungu")
	private String sigungu;
	
	@Column(name = "emd")
	private String emd;
	
	@Column(name = "cat")
	private String cat;
	
	@Column(name = "facilityname")
	private String facilityName;
	
	@Column(name = "basicinfo")
	private String basicInfo;
	
	@Column(name = "roadaddr")
	private String roadAddr;
	
	@Column(name = "landaddr")
	private String landAddr;
	
	@Column(name = "phonenum")
	private String phoneNum;
	
	@Column(name = "website")
	private String website;
	
	@Column(name = "closedday")
	private String closedDay;
	
	@Column(name = "businesshr")
	private String businessHr;
	
	@Column(name = "parkingavail")
	private String parkingAvail;
	
	@Column(name = "admissionfee")
	private String admissionFee;
	
	@Column(name = "petfee")
	private String petFee;
	
	@Column(name = "petavail")
	private String petAvail;
	
	@Column(name = "petexclusive")
	private String petExclusive;
	
	@Column(name = "petsize")
	private String petSize;
	
	@Column(name = "petrestrictions")
	private String petRestrictions;
	
	@Column(name = "indooravail")
	private String indoorAvail;
	
	@Column(name = "outdooravail")
	private String outdoorAvail;
	
	@Column(name = "lat")
	private double lat;
	
	@Column(name = "lng")
	private double lng;
	
}