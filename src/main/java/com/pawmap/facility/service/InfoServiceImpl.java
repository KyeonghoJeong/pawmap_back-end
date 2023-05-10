package com.pawmap.facility.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pawmap.facility.dao.InfoDao;
import com.pawmap.facility.dto.InfoDto;
import com.pawmap.facility.dto.LocationDto;
import com.pawmap.facility.entity.FacilityEntity;

@Service
public class InfoServiceImpl implements InfoService{

	@Autowired
	private InfoDao infoDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Page<InfoDto> getInfoBySingleEmd(String emd, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> infoEntities = infoDao.getInfoBySingleEmd(emd, pageable);

		Page<InfoDto> infoDtos = infoEntities.map(InfoDto::new);
		
		return infoDtos;
	}

	@Override
	public Page<InfoDto> getInfoBySingleCat(String cat, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntityPage = infoDao.getFacilityBySingleCat(cat, pageable);
		
		Page<InfoDto> facilityDtoPage = facilityEntityPage.map(InfoDto::new);
		
		return facilityDtoPage;
	}

	@Override
	public List<LocationDto> getFacilityLocationBySingleEmd(String emd) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntityList = infoDao.getFacilityBySingleEmd(emd);
		
		List<LocationDto> facilityLocationDtoList = facilityEntityList.stream()
				.map(facilityEntity -> modelMapper.map(facilityEntity, LocationDto.class))
				.collect(Collectors.toList());
		
		return facilityLocationDtoList;
	}

	@Override
	public List<LocationDto> getFacilityLocationBySingleCat(String cat) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntityList = infoDao.getFacilityBySingleCat(cat);
		
		List<LocationDto> facilityLocationDtoList = facilityEntityList.stream()
				.map(facilityEntity -> modelMapper.map(facilityEntity, LocationDto.class))
				.collect(Collectors.toList());
		
		return facilityLocationDtoList;
	}
	
}
