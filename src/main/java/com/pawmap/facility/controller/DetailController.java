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
	
	// 시설 id를 파라미터로 받아 해당 시설 정보를 리턴하는 메소드
	@GetMapping("/facility")
	public DetailDto getFacility(@RequestParam Long facilityId) {
		DetailDto detailDto = detailService.getFacility(facilityId); // 시설 정보 메소드 호출
		
		return detailDto;
	}
	
}
