package com.pawmap.dao;

import java.util.List;

import com.pawmap.entity.FacilityEntity;

public interface FacilityDao {

	List<FacilityEntity> getFacilityEntityListBySingleEmd(String emd);
	List<FacilityEntity> getFacilityEntityListBySingleCat(String cat);

}
