package com.pawmap.map.dto;

import com.pawmap.map.entity.FacilityEntity;

import lombok.Getter;
import lombok.Setter;

// �������������� �ϸ�ũ ��ȸ �� �ü� id�� ������ �ü��� �Ϻ� ����

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