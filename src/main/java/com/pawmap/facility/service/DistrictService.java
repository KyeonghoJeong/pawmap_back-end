package com.pawmap.facility.service;

import java.util.List;

import com.pawmap.facility.dto.EmdDto;
import com.pawmap.facility.dto.SidoDto;
import com.pawmap.facility.dto.SigunguDto;

public interface DistrictService {

	List<SidoDto> getSido();

	List<SigunguDto> getSigungu(Long sido_id);

	List<EmdDto> getEmd(Long sigungu_id);

}