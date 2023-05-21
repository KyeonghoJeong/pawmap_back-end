package com.pawmap.facility.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.facility.dto.DetailDto;
import com.pawmap.facility.service.DetailService;

@RestController
@RequestMapping("api")
public class DetailController {

	@Autowired
	private DetailService detailService;
	
	@GetMapping("/facility")
	public DetailDto getFacility(@RequestParam Long facilityId) {
		DetailDto detailDto = detailService.getFacility(facilityId);
		
		return detailDto;
	}
	
}
