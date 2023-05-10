package com.pawmap.facility.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.facility.dto.EmdDto;
import com.pawmap.facility.dto.SidoDto;
import com.pawmap.facility.dto.SigunguDto;
import com.pawmap.facility.service.DistrictService;

@RestController
@RequestMapping("district")
public class DistrictController {
	
	@Autowired
	private DistrictService districtService;

	@GetMapping("/sido")
	public List<SidoDto> getSido(){
		List<SidoDto> sidoDto = districtService.getSido();
		
		return sidoDto;
	}
	
	@GetMapping("/sigungu")
	public List<SigunguDto> getSigungu(@RequestParam Long sidoId){
		List<SigunguDto> sigunguDto = districtService.getSigungu(sidoId);
		
		return sigunguDto;
	}
	
	@GetMapping("/emd")
	public List<EmdDto> getEmd(@RequestParam Long sigunguId){
		List<EmdDto> emdDto = districtService.getEmd(sigunguId);
		
		return emdDto;
	}
	
}