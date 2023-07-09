package com.pawmap.map.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.map.dto.EmdDto;
import com.pawmap.map.dto.SidoDto;
import com.pawmap.map.dto.SigunguDto;
import com.pawmap.map.service.DistrictService;

// 시도, 시군구, 읍면동 각각을 select 메뉴로 선택할 때 각각의 id에 맞게 소속된 행정구역으로 옵션들을 리턴하는 컨트롤러 

@RestController
@RequestMapping("api/map/district")
public class DistrictController {
	
	@Autowired
	private DistrictService districtService;

	@GetMapping("/sidos")
	public List<SidoDto> getSidos(){
		List<SidoDto> sidoDtos = districtService.getSidos();
		
		return sidoDtos;
	}
	
	@GetMapping("/sigungus")
	public List<SigunguDto> getSigungus(@RequestParam Long sidoId){
		List<SigunguDto> sigunguDtos = districtService.getSigungus(sidoId);
		
		return sigunguDtos;
	}
	
	@GetMapping("/emds")
	public List<EmdDto> getEmds(@RequestParam Long sigunguId){
		List<EmdDto> emdDtos = districtService.getEmds(sigunguId);
		
		return emdDtos;
	}
	
}