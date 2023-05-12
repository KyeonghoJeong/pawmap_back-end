package com.pawmap.facility.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.facility.dto.LocationDto;
import com.pawmap.facility.service.LocationService;

@RestController
@RequestMapping("facility/location")
public class LocationController {
	
	@Autowired
	private LocationService locationService;

	@GetMapping("/single")
	public List<LocationDto> getFacilityLocation(
			@RequestParam(required=false) String emd,
			@RequestParam(required=false) String cat){
		
		List<LocationDto> LocationDtos = null;
		
		if(emd != null) {
			LocationDtos = locationService.getLocationBySingleEmd(emd);
		}else if(cat != null) {
			LocationDtos = locationService.getLocationBySingleCat(cat);
		}

		return LocationDtos;
	}
	
}