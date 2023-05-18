package com.pawmap.facility.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.facility.dto.InfoDto;

public interface InfoService {
	
	Page<InfoDto> getInfoBySingleEmd(String emd, Pageable pageable);
	
	Page<InfoDto> getInfoBySingleCat(String cat, Double lat, Double lng, Pageable pageable);

}
