package com.pawmap.facility.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.facility.entity.FacilityEntity;

public interface InfoDao {

	Page<FacilityEntity> getInfoBySingleEmd(String emd, Pageable pageable);

	Page<FacilityEntity> getFacilityBySingleCat(String cat, Pageable pageable);

	List<FacilityEntity> getFacilityBySingleEmd(String emd);

	List<FacilityEntity> getFacilityBySingleCat(String cat);


}