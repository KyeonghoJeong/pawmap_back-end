package com.pawmap.facility.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.facility.dto.InfoDto;
import com.pawmap.facility.dto.LocationDto;

public interface InfoService {
	
	Page<InfoDto> getInfoBySingleEmd(String emd, Pageable pageable);

	Page<InfoDto> getInfoBySingleCat(String cat, Pageable pageable);

	List<LocationDto> getFacilityLocationBySingleEmd(String emd);

	List<LocationDto> getFacilityLocationBySingleCat(String cat);
	
}
