package com.pawmap.map.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawmap.map.dao.DistrictDao;
import com.pawmap.map.dto.EmdDto;
import com.pawmap.map.dto.SidoDto;
import com.pawmap.map.dto.SigunguDto;
import com.pawmap.map.entity.EmdEntity;
import com.pawmap.map.entity.SidoEntity;
import com.pawmap.map.entity.SigunguEntity;

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
	public List<SigunguDto> getSigungu(Long sidoId) {
		// TODO Auto-generated method stub
		List<SigunguEntity> sigunguEntity = districtDao.getSigungu(sidoId);
		
		List<SigunguDto> sigunguDto = sigunguEntity.stream()
				.map(SigunguEntity -> modelMapper.map(SigunguEntity, SigunguDto.class))
				.collect(Collectors.toList());
		
		return sigunguDto;
	}

	@Override
	public List<EmdDto> getEmd(Long sigunguId) {
		// TODO Auto-generated method stub
		List<EmdEntity> emdEntity = districtDao.getEmd(sigunguId);
		
		List<EmdDto> EmdDto = emdEntity.stream()
				.map(EmdEntity -> modelMapper.map(EmdEntity, EmdDto.class))
				.collect(Collectors.toList());
		
		return EmdDto;
	}
	
}
