package com.pawmap.facility.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.facility.dto.InfoDto;

public interface InfoService {
	
	Page<InfoDto> getInfoBySingleEmd(String emd, Pageable pageable);
	
	Page<InfoDto> getInfoBySingleCat(String cat, Double lat, Double lng, Pageable pageable);

	Page<InfoDto> getInfoByGroupSido(String cat, String sido, Pageable pageable);

	Page<InfoDto> getInfoByGroupSigungu(String cat, String sido, String sigungu, Pageable pageable);

	Page<InfoDto> getInfoByGroupEmd(String cat, String sido, String sigungu, String emd, Pageable pageable);

}
