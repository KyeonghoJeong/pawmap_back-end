package com.pawmap.facility.dao;

import java.util.List;

import com.pawmap.facility.entity.FacilityEntity;

public interface LocationDao {

	List<FacilityEntity> getLocationBySingleEmd(String emd);

}