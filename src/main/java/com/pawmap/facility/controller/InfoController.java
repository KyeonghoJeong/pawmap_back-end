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
	
	@GetMapping("/single")
	public Page<InfoDto> getFacilityBySingle(
			@RequestParam(required=false) String emd, 
			@RequestParam(required=false) String cat,
            Pageable pageable){
		
		Page<InfoDto> infoDtos = null;
		
		if(emd != null) {
			infoDtos = infoService.getInfoBySingleEmd(emd, pageable);
		}else if(cat != null) {
			infoDtos = infoService.getInfoBySingleCat(cat, pageable);
		}

		return infoDtos;
	}
	
}