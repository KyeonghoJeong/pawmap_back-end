package com.pawmap.facility.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.pawmap.facility.entity.FacilityEntity;
import com.pawmap.facility.repository.FacilityRepository;

@Repository
public class InfoDaoImpl implements InfoDao {
	
	@Autowired
	private FacilityRepository facilityRepository;

	@Override
	public Page<FacilityEntity> getInfoBySingleEmd(String emd, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = facilityRepository.findByEmd(emd, pageable);

		return facilityEntities;
	}

	@Override
	public Page<FacilityEntity> getInfoBySingleCat(String cat, double lat, double lng, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = facilityRepository.findByCat(cat, lat, lng, pageable);
		
		return facilityEntities;
	}

}
