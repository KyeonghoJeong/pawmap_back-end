package com.pawmap.facility.service;

import java.util.List;

import com.pawmap.facility.dto.LocationDto;

public interface LocationService {

	List<LocationDto> getLocationBySingleEmd(String emd);

	List<LocationDto> getLocationBySingleCat(String cat);

	List<LocationDto> getLocations();

}
