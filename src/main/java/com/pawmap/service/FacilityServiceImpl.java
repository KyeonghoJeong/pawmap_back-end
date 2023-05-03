package com.pawmap.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawmap.dao.FacilityDao;
import com.pawmap.dto.FacilityDto;
import com.pawmap.entity.FacilityEntity;

@Service
public class FacilityServiceImpl implements FacilityService{

	@Autowired
	private FacilityDao facilityDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<FacilityDto> getFacilityDtoBySingleEmd(String emd) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntityList = facilityDao.getFacilityEntityListBySingleEmd(emd);
		
		List<FacilityDto> facilityDtoList = facilityEntityList.stream()
				.map(FacilityEntity -> modelMapper.map(FacilityEntity, FacilityDto.class))
				.collect(Collectors.toList());
		
		return facilityDtoList;
	}

	@Override
	public List<FacilityDto> getFacilityDtoBySingleCat(String cat) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntityList = facilityDao.getFacilityEntityListBySingleCat(cat);
		
		List<FacilityDto> facilityDtoList = facilityEntityList.stream()
				.map(FacilityEntity -> modelMapper.map(FacilityEntity, FacilityDto.class))
				.collect(Collectors.toList());
		
		return facilityDtoList;
	}
	
}
