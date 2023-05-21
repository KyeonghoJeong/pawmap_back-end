package com.pawmap.facility.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.facility.dto.ListDto;

public interface ListService {
	
	Page<ListDto> getListBySingleEmd(String emd, Pageable pageable);
	
	Page<ListDto> getListBySingleCat(String cat, Double lat, Double lng, Pageable pageable);

	Page<ListDto> getListByGroupSido(String cat, String sido, Pageable pageable);

	Page<ListDto> getListByGroupSigungu(String cat, String sido, String sigungu, Pageable pageable);

	Page<ListDto> getListByGroupEmd(String cat, String sido, String sigungu, String emd, Pageable pageable);

}
