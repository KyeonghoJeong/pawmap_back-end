package com.pawmap.map.service;

import java.util.List;

import com.pawmap.map.dto.EmdDto;
import com.pawmap.map.dto.SidoDto;
import com.pawmap.map.dto.SigunguDto;

public interface DistrictService {

	List<SidoDto> getSidos();

	List<SigunguDto> getSigungus(Long sido_id);

	List<EmdDto> getEmds(Long sigungu_id);

}
