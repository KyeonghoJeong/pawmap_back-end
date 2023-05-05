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
	private String facility_name;
	private String basic_info;
	private String road_addr;
	private double lat;
	private double lng;
	
}