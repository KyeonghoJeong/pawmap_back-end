package com.pawmap.map.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pawmap.map.dao.FacilityDao;
import com.pawmap.map.dto.FacilityCardDto;
import com.pawmap.map.dto.FacilityDto;
import com.pawmap.map.dto.FacilityLocationDto;
import com.pawmap.map.entity.FacilityEntity;

@Service
public class FacilityServiceImpl implements FacilityService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private FacilityDao facilityDao;
	
	@Override
	public FacilityDto getFacility(Long facilityId) {
		// TODO Auto-generated method stub
		FacilityEntity facilityEntity = facilityDao.getFacilityInformation(facilityId);
		
		// modelMapper ��ü�� ��ƼƼ ��ü�� dto ��ü�� ����
		FacilityDto facilityDtos = modelMapper.map(facilityEntity, FacilityDto.class);
		
		return facilityDtos;
	}
	
	@Override
	public List<FacilityLocationDto> getFacilityLocations(String cat, String sido, String sigungu, String emd,
			Double lat, Double lng) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntities = facilityDao.getFacilityLocations(cat, sido, sigungu, emd, lat, lng);
		
		// List ���� �� FacilityEntity ��ü�� FacilityDto ��ü�� ���� �� �ٽ� List������ �����
		List<FacilityLocationDto> facilityLocationDtos = facilityEntities.stream()
				.map(facilityEntity -> modelMapper.map(facilityEntity, FacilityLocationDto.class))
				.collect(Collectors.toList());
		
		return facilityLocationDtos;
	}

	@Override
	public Page<FacilityCardDto> getFacilities(String cat, String sido, String sigungu, String emd, Double lat, Double lng,
			Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = facilityDao.getFacilities(cat, sido, sigungu, emd, lat, lng, pageable);
		
		// Page �ɼ� �״�� ������ FacilityEntity ��ü ������ FacilityDto Ŭ������ �����ڸ� �̿� ���� 
		Page<FacilityCardDto> facilityCardDtos = facilityEntities.map(FacilityCardDto::new);
		
		return facilityCardDtos;
	}

}
