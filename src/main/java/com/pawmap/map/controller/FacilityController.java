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

// �ݷ����� ���ݰ��� �ü��� ���, ����, ��ġ api

@RestController
@RequestMapping("api/map")
public class FacilityController {

	@Autowired
	private FacilityService facilityService;
	
	// �ü� id�� �Ķ���ͷ� �޾� �ش� �ü��� �������� �����ϴ� �޼ҵ�
	@GetMapping("/facility")
	public FacilityDto getFacility(@RequestParam Long facilityId) {
		FacilityDto facilityDtos = facilityService.getFacility(facilityId); // �ü� ���� �޼ҵ� ȣ��
		
		return facilityDtos;
	}
	
	// ī�װ�, �õ�, �ñ���, ���鵿�� �´� �ݷ����� ���ݰ��� �ü� ����� ������ �浵�� �����ִ� �޼ҵ�
	// �� �̸� �˻�, ī�װ� ����, select �޴� ���ÿ� �°� �������ֱ� ���� �� �Ķ������ ���� ���� ��ȸ�Ͽ� ����
	// ��ġ ������ ���� FacilityLocationDto���� ����Ʈ�� ����
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
	
	// ī�װ�, �õ�, �ñ���, ���鵿�� �´� �ݷ����� ���ݰ��� �ü� ����� �����ִ� �޼ҵ�
	// �� �̸� �˻�, ī�װ� ����, select �޴� ���ÿ� �°� �������ֱ� ���� �� �Ķ������ ���� ���� ��ȸ�Ͽ� ����
	// pagination�� ���� Page������ ����
	@GetMapping("/facilities")
	public Page<FacilityDto> getFacilities(
			@RequestParam(required=false) String cat,
			@RequestParam(required=false) String sido,
			@RequestParam(required=false) String sigungu,
			@RequestParam(required=false) String emd, 
			@RequestParam(required=false) Double lat, 
			@RequestParam(required=false) Double lng,
			Pageable pageable){
		
		// ���� �޼ҵ� ȣ��
		Page<FacilityDto> facilityDtos = facilityService.getFacilities(cat, sido, sigungu, emd, lat, lng, pageable);
		
		return facilityDtos;
	}
	
}
