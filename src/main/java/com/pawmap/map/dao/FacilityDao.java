package com.pawmap.map.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.map.entity.FacilityEntity;

public interface FacilityDao {
	
	FacilityEntity getFacilityInformation(Long facilityId);

	Page<FacilityEntity> getFacilities(String cat, String sido, String sigungu, String emd, Double lat, Double lng,
			Pageable pageable);

	List<FacilityEntity> getFacilityLocations(String cat, String sido, String sigungu, String emd, Double lat,
			Double lng);

}
