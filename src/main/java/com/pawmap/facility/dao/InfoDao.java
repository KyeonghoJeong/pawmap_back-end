package com.pawmap.facility.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.facility.entity.FacilityEntity;

public interface InfoDao {

	Page<FacilityEntity> getInfoBySingleEmd(String emd, Pageable pageable);

	Page<FacilityEntity> getInfoBySingleCat(String cat, double lat, double lng, Pageable pageable);

}