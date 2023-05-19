package com.pawmap.facility.dao;

import java.util.List;

import com.pawmap.facility.entity.FacilityEntity;

public interface LocationDao {

	List<FacilityEntity> getLocationBySingleEmd(String emd);
	
	List<FacilityEntity> getLocationBySingleCat(String cat);

	List<FacilityEntity> getLocationByGroupSido(String cat, String sido);

	List<FacilityEntity> getLocationByGroupSigungu(String cat, String sido, String sigungu);

	List<FacilityEntity> getLocationByGroupEmd(String cat, String sido, String sigungu, String emd);

}