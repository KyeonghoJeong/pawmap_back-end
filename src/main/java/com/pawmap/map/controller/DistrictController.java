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

// �õ�, �ñ���, ���鵿 ������ select �޴��� ������ �� ������ id�� �°� �Ҽӵ� ������������ �ɼǵ��� �����ϴ� ��Ʈ�ѷ� 

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