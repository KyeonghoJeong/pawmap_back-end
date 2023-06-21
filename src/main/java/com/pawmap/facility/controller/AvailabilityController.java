package com.pawmap.facility.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.facility.repository.AvailabilityRepository;

@RestController
@RequestMapping("api/facilities")
public class AvailabilityController {
	
	@Autowired
	private AvailabilityRepository avaiabilityRepository;
	
	@GetMapping("/availability")
	public Long checkFacilityAvailable(
			@RequestParam(required=false) String cat,
			@RequestParam(required=false) String sido,
			@RequestParam(required=false) String sigungu,
			@RequestParam(required=false) String emd) {
		
		Long count = null;
		
		if(cat == null && sido == null && sigungu == null && emd != null) {
			count = avaiabilityRepository.countByEmd(emd);
		}else if(cat != null && sido != null && sigungu == null && emd == null) {
			count = avaiabilityRepository.countByCatAndSido(cat, sido);
		}else if(cat != null && sido != null && sigungu != null && emd == null) {
			count = avaiabilityRepository.countByCatAndSidoAndSigungu(cat, sido, sigungu);
		}else if(cat != null && sido != null && sigungu != null && emd != null) {
			count = avaiabilityRepository.countByCatAndSidoAndSigunguAndEmd(cat, sido, sigungu, emd);
		}
		
		return count;
	}
}
