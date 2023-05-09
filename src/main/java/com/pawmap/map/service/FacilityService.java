package com.pawmap.map.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.map.dto.FacilityDto;
import com.pawmap.map.dto.FacilityLocationDto;

public interface FacilityService {
	
	Page<FacilityDto> getFacilityBySingleEmd(String emd, Pageable pageable);

	Page<FacilityDto> getFacilityBySingleCat(String cat, Pageable pageable);

	List<FacilityLocationDto> getFacilityLocationBySingleEmd(String emd);

	List<FacilityLocationDto> getFacilityLocationBySingleCat(String cat);
	
}
