package com.pawmap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.dto.FacilityDto;
import com.pawmap.service.FacilityService;

@RestController
@RequestMapping("facility")
public class FacilityController {
	
	@Autowired
	private FacilityService facilityService;
	
	@GetMapping("/single/emd")
	public List<FacilityDto> getFacilityBySingleEmd(@RequestParam String emd){
		List<FacilityDto> facilityDtoList = facilityService.getFacilityDtoBySingleEmd(emd);
		
		return facilityDtoList;
	}
	
	@GetMapping("/single/cat")
	public List<FacilityDto> getFacilityBySingleCat(@RequestParam String cat){
		List<FacilityDto> facilityDtoList = facilityService.getFacilityDtoBySingleCat(cat);
		
		return facilityDtoList;
	}
	
}