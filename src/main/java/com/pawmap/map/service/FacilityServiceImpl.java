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
		
		// modelMapper 객체로 엔티티 객체를 dto 객체로 매핑
		FacilityDto facilityDtos = modelMapper.map(facilityEntity, FacilityDto.class);
		
		return facilityDtos;
	}
	
	@Override
	public List<FacilityLocationDto> getFacilityLocations(String cat, String sido, String sigungu, String emd,
			Double lat, Double lng) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntities = facilityDao.getFacilityLocations(cat, sido, sigungu, emd, lat, lng);
		
		// List 내의 각 FacilityEntity 객체를 FacilityDto 객체로 매핑 후 다시 List형으로 만들기
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
		
		// Page 옵션 그대로 내부의 FacilityEntity 객체 각각을 FacilityDto 클래스의 생성자를 이용 매핑 
		Page<FacilityCardDto> facilityCardDtos = facilityEntities.map(FacilityCardDto::new);
		
		return facilityCardDtos;
	}

}
