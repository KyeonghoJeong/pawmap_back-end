package com.pawmap.facility.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawmap.facility.dao.LocationDao;
import com.pawmap.facility.dto.LocationDto;
import com.pawmap.facility.entity.FacilityEntity;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationDao locationDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<LocationDto> getLocationBySingleEmd(String emd) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntities = locationDao.getLocationBySingleEmd(emd);
		
		List<LocationDto> LocationDtos = facilityEntities.stream()
				.map(facilityEntity -> modelMapper.map(facilityEntity, LocationDto.class))
				.collect(Collectors.toList());
		
		return LocationDtos;
	}

	@Override
	public List<LocationDto> getLocationBySingleCat(String cat) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntities = locationDao.getLocationBySingleCat(cat);
		
		List<LocationDto> LocationDtos = facilityEntities.stream()
				.map(facilityEntity -> modelMapper.map(facilityEntity, LocationDto.class))
				.collect(Collectors.toList());
		
		return LocationDtos;
	}

}