package com.pawmap.map.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.map.entity.FacilityEntity;

public interface FacilityDao {

	Page<FacilityEntity> getFacilityBySingleEmd(String emd, Pageable pageable);

	Page<FacilityEntity> getFacilityBySingleCat(String cat, Pageable pageable);

	List<FacilityEntity> getFacilityBySingleEmd(String emd);

	List<FacilityEntity> getFacilityBySingleCat(String cat);


}