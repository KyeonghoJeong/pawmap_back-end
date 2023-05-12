package com.pawmap.facility.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.facility.dto.InfoDto;
import com.pawmap.facility.service.InfoService;

@RestController
@RequestMapping("facility/info")
public class InfoController {
	
	@Autowired
	private InfoService infoService;
	
	@GetMapping("/single/emd")
	public Page<InfoDto> getInfoBySingleEmd(@RequestParam String emd, Pageable pageable){
		Page<InfoDto> infoDtos = infoService.getInfoBySingleEmd(emd, pageable);
		
		return infoDtos;
	}
	
	@GetMapping("/single/cat")
	public Page<InfoDto> getInfoBySingleCat(@RequestParam String cat, @RequestParam double lat, @RequestParam double lng, Pageable pageable){
		Page<InfoDto> infoDtos = infoService.getInfoBySingleCat(cat, lat, lng, pageable);
		
		return infoDtos;
	}
	
}