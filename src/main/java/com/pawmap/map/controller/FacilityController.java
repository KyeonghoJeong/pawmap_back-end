package com.pawmap.map.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.map.dto.FacilityDto;
import com.pawmap.map.dto.FacilityLocationDto;
import com.pawmap.map.service.FacilityService;

@RestController
@RequestMapping("facility")
public class FacilityController {
	
	@Autowired
	private FacilityService facilityService;
	
	@GetMapping("/single")
	public Page<FacilityDto> getFacilityBySingle(
			@RequestParam(required=false) String emd, 
			@RequestParam(required=false) String cat,
            Pageable pageable){
		
		Page<FacilityDto> facilityDtoList = null;
		
		if(emd != null) {
			facilityDtoList = facilityService.getFacilityBySingleEmd(emd, pageable);
		}else if(cat != null) {
			facilityDtoList = facilityService.getFacilityBySingleCat(cat, pageable);
		}

		return facilityDtoList;
	}
	
	@GetMapping("/single/location")
	public List<FacilityLocationDto> getFacilityLocationBySingle(
			@RequestParam(required=false) String emd,
			@RequestParam(required=false) String cat){
		
		List<FacilityLocationDto> facilityLocationDtoList = null;
		
		if(emd != null) {
			facilityLocationDtoList = facilityService.getFacilityLocationBySingleEmd(emd);
		}else if (cat != null){
			facilityLocationDtoList = facilityService.getFacilityLocationBySingleCat(cat);
		}
		
		return facilityLocationDtoList;
		
	}
	
}