package com.pawmap.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Table(name = "facility")
@Entity
public class FacilityEntity {
	
	@Id
	private Long facility_id;
	private String sido;
	private String sigungu;
	private String emd;
	private String cat;
	private String facility_name;
	private String basic_info;
	private String road_addr;
	private String land_addr;
	private String phone_num;
	private String website;
	private String closed_day;
	private String business_hr;
	private String parking_avail;
	private String admission_fee;
	private String pet_fee;
	private String pet_avail;
	private String pet_exclusive;
	private String pet_size;
	private String pet_restrictions;
	private String indoor_avail;
	private String outdoor_avail;
	private double lat;
	private double lng;
	
}
