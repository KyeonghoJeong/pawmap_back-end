package com.pawmap.service;

import java.util.List;

import com.pawmap.dto.EmdDto;
import com.pawmap.dto.SidoDto;
import com.pawmap.dto.SigunguDto;

public interface DistrictService {

	List<SidoDto> getSido();

	List<SigunguDto> getSigungu(Long sido_id);

	List<EmdDto> getEmd(Long sigungu_id);

}
