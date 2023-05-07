package com.pawmap.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawmap.dao.DistrictDao;
import com.pawmap.dto.EmdDto;
import com.pawmap.dto.SidoDto;
import com.pawmap.dto.SigunguDto;
import com.pawmap.entity.EmdEntity;
import com.pawmap.entity.SidoEntity;
import com.pawmap.entity.SigunguEntity;

@Service
public class DistrictServiceImpl implements DistrictService {
	
	@Autowired
	private DistrictDao districtDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<SidoDto> getSido() {
		// TODO Auto-generated method stub
		List<SidoEntity> sidoEntity = districtDao.getSido();
		
		List<SidoDto> sidoDto = sidoEntity.stream()
				.map(SidoEntity -> modelMapper.map(SidoEntity, SidoDto.class))
				.collect(Collectors.toList());
		
		return sidoDto;
	}

	@Override
	public List<SigunguDto> getSigungu(Long sido_id) {
		// TODO Auto-generated method stub
		List<SigunguEntity> sigunguEntity = districtDao.getSigungu(sido_id);
		
		List<SigunguDto> sigunguDto = sigunguEntity.stream()
				.map(SigunguEntity -> modelMapper.map(SigunguEntity, SigunguDto.class))
				.collect(Collectors.toList());
		
		return sigunguDto;
	}

	@Override
	public List<EmdDto> getEmd(Long sigungu_id) {
		// TODO Auto-generated method stub
		List<EmdEntity> emdEntity = districtDao.getEmd(sigungu_id);
		
		List<EmdDto> EmdDto = emdEntity.stream()
				.map(EmdEntity -> modelMapper.map(EmdEntity, EmdDto.class))
				.collect(Collectors.toList());
		
		return EmdDto;
	}
	
}
