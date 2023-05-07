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
	
	@GetMapping("/single")
	public List<FacilityDto> getFacilityBySingleEmd(@RequestParam(required=false) String emd, @RequestParam(required=false) String cat){
		List<FacilityDto> facilityDtoList = null;
		
		if(emd != null) {
			facilityDtoList = facilityService.getFacilityDtoBySingleEmd(emd);
		}else if(cat != null){
			facilityDtoList = facilityService.getFacilityDtoBySingleCat(cat);
		}
		
		return facilityDtoList;
	}
	
	@GetMapping("/group")
	public String getFacility(@RequestParam(required=false) String sido, 
			@RequestParam(required=false) String sigungu, 
			@RequestParam(required=false) String emd, 
			@RequestParam(required=false) String cat){
		
		String result = "";
		
		if(sigungu == null) {
			// only sido
			result = "only sido " + sido;
		}else if(emd == null) {
			// sido and sigungu
			result = "sido and sigungu " + sido + sigungu;
		}else if(cat == null) {
			// sido, sigungu and emd
			result = "sido, sigungu and emd " + sido + sigungu + emd;
		}else if(cat != null) {
			// everything
			result = "everything " + sido + sigungu + emd + cat;
		}
		
		return result;
	}
	
}