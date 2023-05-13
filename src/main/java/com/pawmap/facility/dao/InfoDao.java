package com.pawmap.facility.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.facility.entity.FacilityEntity;

public interface InfoDao {

	Page<FacilityEntity> getInfoBySingleEmd(String emd, Pageable pageable);

	Page<FacilityEntity> getInfoBySingleCat(String cat, Double lat, Double lng, Pageable pageable);

	Page<FacilityEntity> getInfoAll(Double lat, Double lng, Pageable pageable);

}