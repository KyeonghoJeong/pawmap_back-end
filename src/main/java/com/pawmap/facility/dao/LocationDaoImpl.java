package com.pawmap.facility.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pawmap.facility.entity.FacilityEntity;
import com.pawmap.facility.repository.FacilityRepository;

@Repository
public class LocationDaoImpl implements LocationDao {

	@Autowired
	private FacilityRepository facilityRepository;
	
	@Override
	public List<FacilityEntity> getLocationBySingleEmd(String emd) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntities = facilityRepository.findByEmd(emd);
		
		return facilityEntities;
	}

	@Override
	public List<FacilityEntity> getLocationBySingleCat(String cat) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntities = facilityRepository.findByCat(cat);
		
		return facilityEntities;
	}

	@Override
	public List<FacilityEntity> getLocations() {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntities = facilityRepository.findByLocation();
		
		return facilityEntities;
	}

}