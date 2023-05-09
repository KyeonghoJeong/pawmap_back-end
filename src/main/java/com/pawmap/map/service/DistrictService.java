package com.pawmap.map.service;

import java.util.List;

import com.pawmap.map.dto.EmdDto;
import com.pawmap.map.dto.SidoDto;
import com.pawmap.map.dto.SigunguDto;

public interface DistrictService {

	List<SidoDto> getSido();

	List<SigunguDto> getSigungu(Long sido_id);

	List<EmdDto> getEmd(Long sigungu_id);

}
