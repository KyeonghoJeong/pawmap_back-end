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
@RequestMapping("api/facilities")
public class LocationController {
	
	@Autowired
	private LocationService locationService;

	@GetMapping("/locations")
	public List<LocationDto> getFacilityLocation(
			@RequestParam(required=false) String cat,
			@RequestParam(required=false) String sido,
			@RequestParam(required=false) String sigungu,
			@RequestParam(required=false) String emd, 
			@RequestParam(required=false) Double lat, 
			@RequestParam(required=false) Double lng){
		
		List<LocationDto> LocationDtos = null;
		
		if(cat == null && sido == null && sigungu == null && emd != null) {
			LocationDtos = locationService.getLocationBySingleEmd(emd);
		}	
		
		if(cat != null && sido == null && sigungu == null && emd == null) {
			LocationDtos = locationService.getLocationBySingleCat(cat);
		}
		
		if(cat != null && sido != null && sigungu == null && emd == null) {
			LocationDtos = locationService.getLocationByGroupSido(cat, sido);
		}
		
		if(cat != null && sido != null && sigungu != null && emd == null) {
			LocationDtos = locationService.getLocationByGroupSigungu(cat, sido, sigungu);
		}
		
		if(cat != null && sido != null && sigungu != null && emd != null) {
			LocationDtos = locationService.getLocationByGroupEmd(cat, sido, sigungu, emd);
		}
		
		return LocationDtos;
	}
	
}