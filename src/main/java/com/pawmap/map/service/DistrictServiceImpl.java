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
	private ModelMapper modelMapper;
	
	@Autowired
	private DistrictDao districtDao;

	@Override
	public List<SidoDto> getSidos() {
		// TODO Auto-generated method stub
		
		// DB ���̺� ���� ��� �õ� ���� ����
		List<SidoEntity> sidoEntities = districtDao.getSidos();
		
		// List ���� �� entity���� dto�� ����
		List<SidoDto> sidoDtos = sidoEntities.stream()
				.map(SidoEntity -> modelMapper.map(SidoEntity, SidoDto.class))
				.collect(Collectors.toList());
		
		return sidoDtos;
	}

	@Override
	public List<SigunguDto> getSigungus(Long sidoId) {
		// TODO Auto-generated method stub
		
		// �Ķ���ͷ� ���� �õ� id�� �´� �ñ��� ������ ����
		List<SigunguEntity> sigunguEntities = districtDao.getSigungus(sidoId);
		
		// List ���� �� entity���� dto�� ����
		List<SigunguDto> sigunguDtos = sigunguEntities.stream()
				.map(SigunguEntity -> modelMapper.map(SigunguEntity, SigunguDto.class))
				.collect(Collectors.toList());
		
		return sigunguDtos;
	}

	@Override
	public List<EmdDto> getEmds(Long sigunguId) {
		// TODO Auto-generated method stub
		
		// �Ķ���ͷ� ���� �ñ��� id�� �´� ���鵿 ������ ����
		List<EmdEntity> emdEntities = districtDao.getEmds(sigunguId);
		
		// List ���� �� entity���� dto�� ����
		List<EmdDto> EmdDtos = emdEntities.stream()
				.map(EmdEntity -> modelMapper.map(EmdEntity, EmdDto.class))
				.collect(Collectors.toList());
		
		return EmdDtos;
	}
	
}
