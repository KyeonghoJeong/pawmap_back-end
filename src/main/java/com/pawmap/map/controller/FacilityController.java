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

// 반려동물 동반가능 시설의 목록, 정보, 위치 api

@RestController
@RequestMapping("api/map")
public class FacilityController {

	@Autowired
	private FacilityService facilityService;
	
	// 시설 id를 파라미터로 받아 해당 시설의 상세정보를 리턴하는 메소드
	@GetMapping("/facility")
	public FacilityDto getFacility(@RequestParam Long facilityId) {
		FacilityDto facilityDtos = facilityService.getFacility(facilityId); // 시설 정보 메소드 호출
		
		return facilityDtos;
	}
	
	// 카테고리, 시도, 시군구, 읍면동에 맞는 반려동물 동반가능 시설 목록의 위도와 경도를 보내주는 메소드
	// 동 이름 검색, 카테고리 선택, select 메뉴 선택에 맞게 리턴해주기 위해 각 파라미터의 유무 별로 조회하여 리턴
	// 위치 정보를 담은 FacilityLocationDto형의 리스트로 리턴
	@GetMapping("/facility/locations")
	public List<FacilityLocationDto> getFacilityLocations(
			@RequestParam(required=false) String cat,
			@RequestParam(required=false) String sido,
			@RequestParam(required=false) String sigungu,
			@RequestParam(required=false) String emd, 
			@RequestParam(required=false) Double lat, 
			@RequestParam(required=false) Double lng){
		
		List<FacilityLocationDto> facilityLocationDtos = facilityService.getFacilityLocations(cat, sido, sigungu, emd, lat, lng);
		
		return facilityLocationDtos;
	}
	
	// 카테고리, 시도, 시군구, 읍면동에 맞는 반려동물 동반가능 시설 목록을 보내주는 메소드
	// 동 이름 검색, 카테고리 선택, select 메뉴 선택에 맞게 리턴해주기 위해 각 파라미터의 유무 별로 조회하여 리턴
	// pagination을 위해 Page형으로 리턴
	@GetMapping("/facilities")
	public Page<FacilityDto> getFacilities(
			@RequestParam(required=false) String cat,
			@RequestParam(required=false) String sido,
			@RequestParam(required=false) String sigungu,
			@RequestParam(required=false) String emd, 
			@RequestParam(required=false) Double lat, 
			@RequestParam(required=false) Double lng,
			Pageable pageable){
		
		// 서비스 메소드 호출
		Page<FacilityDto> facilityDtos = facilityService.getFacilities(cat, sido, sigungu, emd, lat, lng, pageable);
		
		return facilityDtos;
	}
	
}
