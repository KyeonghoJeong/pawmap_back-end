package com.pawmap.facility.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.facility.dto.ListDto;
import com.pawmap.facility.service.ListService;

@RestController
@RequestMapping("api")
public class ListController {
	
	@Autowired
	private ListService listService;
	
	@GetMapping("/facilities")
	public Page<ListDto> getList(
			@RequestParam(required=false) String cat,
			@RequestParam(required=false) String sido,
			@RequestParam(required=false) String sigungu,
			@RequestParam(required=false) String emd, 
			@RequestParam(required=false) Double lat, 
			@RequestParam(required=false) Double lng,
			Pageable pageable){
		
		Page<ListDto> listDtos = null;
		
		// 메인 페이지에서 동 이름 검색 시
		if(cat == null && sido == null && sigungu == null && emd != null && lat == null && lng == null) {
			listDtos = listService.getListBySingleEmd(emd, pageable);
		}	
		
		// 메인 페이지에서 카테고리 클릭 시
		if(cat != null && sido == null && sigungu == null && emd == null && lat != null && lng != null) {
			listDtos = listService.getListBySingleCat(cat, lat, lng, pageable);
		}
		
		// 시도 선택 시
		if(cat != null && sido != null && sigungu == null && emd == null && lat == null && lng == null) {
			listDtos = listService.getListByGroupSido(cat, sido, pageable);
		}
		
		// 시군구 선택 시
		if(cat != null && sido != null && sigungu != null && emd == null && lat == null && lng == null) {
			listDtos = listService.getListByGroupSigungu(cat, sido, sigungu, pageable);
		}
		
		// 읍면동 선택 시
		if(cat != null && sido != null && sigungu != null && emd != null && lat == null && lng == null) {
			listDtos = listService.getListByGroupEmd(cat, sido, sigungu, emd, pageable);
		}
		
		return listDtos;
	}

}