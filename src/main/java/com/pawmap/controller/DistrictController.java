package com.pawmap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.dto.EmdDto;
import com.pawmap.dto.SidoDto;
import com.pawmap.dto.SigunguDto;
import com.pawmap.service.DistrictService;

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
	public List<SigunguDto> getSigungu(@RequestParam Long sido_id){
		List<SigunguDto> sigunguDto = districtService.getSigungu(sido_id);
		
		return sigunguDto;
	}
	
	@GetMapping("/emd")
	public List<EmdDto> getEmd(@RequestParam Long sigungu_id){
		List<EmdDto> emdDto = districtService.getEmd(sigungu_id);
		
		return emdDto;
	}
	
}