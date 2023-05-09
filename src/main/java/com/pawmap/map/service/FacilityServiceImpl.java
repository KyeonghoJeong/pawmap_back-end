package com.pawmap.map.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pawmap.map.dao.FacilityDao;
import com.pawmap.map.dto.FacilityDto;
import com.pawmap.map.dto.FacilityLocationDto;
import com.pawmap.map.entity.FacilityEntity;

@Service
public class FacilityServiceImpl implements FacilityService{

	@Autowired
	private FacilityDao facilityDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Page<FacilityDto> getFacilityBySingleEmd(String emd, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntityPage = facilityDao.getFacilityBySingleEmd(emd, pageable);

		Page<FacilityDto> facilityDtoPage = facilityEntityPage.map(FacilityDto::new);
		
		return facilityDtoPage;
	}

	@Override
	public Page<FacilityDto> getFacilityBySingleCat(String cat, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntityPage = facilityDao.getFacilityBySingleCat(cat, pageable);
		
		Page<FacilityDto> facilityDtoPage = facilityEntityPage.map(FacilityDto::new);
		
		return facilityDtoPage;
	}

	@Override
	public List<FacilityLocationDto> getFacilityLocationBySingleEmd(String emd) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntityList = facilityDao.getFacilityBySingleEmd(emd);
		
		List<FacilityLocationDto> facilityLocationDtoList = facilityEntityList.stream()
				.map(facilityEntity -> modelMapper.map(facilityEntity, FacilityLocationDto.class))
				.collect(Collectors.toList());
		
		return facilityLocationDtoList;
	}

	@Override
	public List<FacilityLocationDto> getFacilityLocationBySingleCat(String cat) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntityList = facilityDao.getFacilityBySingleCat(cat);
		
		List<FacilityLocationDto> facilityLocationDtoList = facilityEntityList.stream()
				.map(facilityEntity -> modelMapper.map(facilityEntity, FacilityLocationDto.class))
				.collect(Collectors.toList());
		
		return facilityLocationDtoList;
	}
	
}
