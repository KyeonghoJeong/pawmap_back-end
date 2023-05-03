package com.pawmap.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pawmap.entity.FacilityEntity;
import com.pawmap.repository.FacilityRepository;

@Repository
public class FacilityDaoImpl implements FacilityDao {
	
	@Autowired
	private FacilityRepository facilityRepository;

	@Override
	public List<FacilityEntity> getFacilityEntityListBySingleEmd(String emd) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntityList = facilityRepository.findByEmd(emd);
		
		return facilityEntityList;
	}

	@Override
	public List<FacilityEntity> getFacilityEntityListBySingleCat(String cat) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntityList = facilityRepository.findByCat(cat);
		
		return facilityEntityList;
	}

}
