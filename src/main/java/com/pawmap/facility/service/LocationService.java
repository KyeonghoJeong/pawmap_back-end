package com.pawmap.facility.service;

import java.util.List;

import com.pawmap.facility.dto.LocationDto;

public interface LocationService {

	List<LocationDto> getLocationBySingleEmd(String emd);
	
	List<LocationDto> getLocationBySingleCat(String cat);

	List<LocationDto> getLocationByGroupSido(String cat, String sido);

	List<LocationDto> getLocationByGroupSigungu(String cat, String sido, String sigungu);

	List<LocationDto> getLocationByGroupEmd(String cat, String sido, String sigungu, String emd);

}
