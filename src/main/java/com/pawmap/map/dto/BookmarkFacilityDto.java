package com.pawmap.map.dto;

import com.pawmap.map.entity.FacilityEntity;

import lombok.Getter;
import lombok.Setter;

// 마이페이지에서 북마크 조회 시 시설 id로 가져올 시설의 일부 정보

@Getter
@Setter
public class BookmarkFacilityDto {

	private Long facilityId;
	private String cat;
	private String facilityName;
	private String roadAddr;
	
	public BookmarkFacilityDto(FacilityEntity facilityEntity) {
		this.facilityId = facilityEntity.getFacilityId();
		this.cat = facilityEntity.getCat();
		this.facilityName = facilityEntity.getFacilityName();
		this.roadAddr = facilityEntity.getRoadAddr();
	}
	
}