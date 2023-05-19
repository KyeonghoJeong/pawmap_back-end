package com.pawmap.facility.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.facility.dto.InfoDto;
import com.pawmap.facility.repository.InfoRepository;
import com.pawmap.facility.service.InfoService;

@RestController
@RequestMapping("api")
public class InfoController {
	
	@Autowired
	private InfoService infoService;
	
	@Autowired
	private InfoRepository infoRepository;
	
	@GetMapping("/facilities/availability")
	public Long checkFacilityAvailable(@RequestParam String emd) {
		Long count = infoRepository.countByEmd(emd);
		
		return count;
	}
	
	@GetMapping("/facilities")
	public Page<InfoDto> getInfo(
			@RequestParam(required=false) String cat,
			@RequestParam(required=false) String sido,
			@RequestParam(required=false) String sigungu,
			@RequestParam(required=false) String emd, 
			@RequestParam(required=false) Double lat, 
			@RequestParam(required=false) Double lng,
			Pageable pageable){
		
		Page<InfoDto> infoDtos = null;
		
		// ���� ���������� �� �̸� �˻� ��
		if(cat == null && sido == null && sigungu == null && emd != null && lat == null && lng == null) {
			infoDtos = infoService.getInfoBySingleEmd(emd, pageable);
		}	
		
		// ���� ���������� ī�װ� Ŭ�� ��
		if(cat != null && sido == null && sigungu == null && emd == null && lat != null && lng != null) {
			infoDtos = infoService.getInfoBySingleCat(cat, lat, lng, pageable);
		}
		
		// �õ� ���� ��
		if(cat != null && sido != null && sigungu == null && emd == null && lat == null && lng == null) {
			infoDtos = infoService.getInfoByGroupSido(cat, sido, pageable);
		}
		
		// �ñ��� ���� ��
		if(cat != null && sido != null && sigungu != null && emd == null && lat == null && lng == null) {
			infoDtos = infoService.getInfoByGroupSigungu(cat, sido, sigungu, pageable);
		}
		
		// ���鵿 ���� ��
		if(cat != null && sido != null && sigungu != null && emd != null && lat == null && lng == null) {
			infoDtos = infoService.getInfoByGroupEmd(cat, sido, sigungu, emd, pageable);
		}
		
		return infoDtos;
	}

}