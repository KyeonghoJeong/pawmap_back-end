package com.pawmap.map.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.map.dto.FacilityCardDto;
import com.pawmap.map.dto.FacilityDto;
import com.pawmap.map.dto.FacilityLocationDto;

public interface FacilityService {
	
	FacilityDto getFacilityInformation(Long facilityId);
	
	List<FacilityLocationDto> getFacilityLocations(String cat, String sido, String sigungu, String emd, Double lat,
			Double lng);

	Page<FacilityCardDto> getFacilities(String cat, String sido, String sigungu, String emd, Double lat, Double lng,
			Pageable pageable);

}
