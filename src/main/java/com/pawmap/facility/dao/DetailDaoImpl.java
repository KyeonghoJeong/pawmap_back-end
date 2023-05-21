package com.pawmap.facility.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pawmap.facility.entity.FacilityEntity;
import com.pawmap.facility.repository.DetailRepository;

@Repository
public class DetailDaoImpl implements DetailDao {
	
	@Autowired
	private DetailRepository detailRepository;

	@Override
	public FacilityEntity getFacility(Long facilityId) {
		// TODO Auto-generated method stub
		Optional<FacilityEntity> facilityEntity = detailRepository.findById(facilityId);
		
		FacilityEntity facility = new FacilityEntity();
		
		if(facilityEntity.isPresent()) {
			facility = facilityEntity.get();
		}
		
		return facility;
	}

}
