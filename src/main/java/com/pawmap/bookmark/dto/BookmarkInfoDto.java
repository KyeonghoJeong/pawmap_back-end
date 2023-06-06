package com.pawmap.bookmark.dto;

import com.pawmap.facility.entity.FacilityEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkInfoDto {

	private Long facilityId;
	private String cat;
	private String facilityName;
	private String roadAddr;
	
	public BookmarkInfoDto(FacilityEntity facilityEntity) {
		this.facilityId = facilityEntity.getFacilityId();
		this.cat = facilityEntity.getCat();
		this.facilityName = facilityEntity.getFacilityName();
		this.roadAddr = facilityEntity.getRoadAddr();
	}
	
}