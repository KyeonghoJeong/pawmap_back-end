package com.pawmap.service;

import java.util.List;

import com.pawmap.dto.FacilityDto;

public interface FacilityService {
	
	List<FacilityDto> getFacilityDtoBySingleEmd(String emd);
	List<FacilityDto> getFacilityDtoBySingleCat(String cat);
	
}
