package com.pawmap.facility.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.facility.entity.FacilityEntity;

public interface ListDao {
	
	Page<FacilityEntity> getListBySingleEmd(String emd, Pageable pageable);

	Page<FacilityEntity> getListBySingleCat(String cat, Double lat, Double lng, Pageable pageable);

	Page<FacilityEntity> getListByGroupSido(String cat, String sido, Pageable pageable);

	Page<FacilityEntity> getListByGroupSigungu(String cat, String sido, String sigungu, Pageable pageable);

	Page<FacilityEntity> getListByGroupEmd(String cat, String sido, String sigungu, String emd, Pageable pageable);

}