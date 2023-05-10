package com.pawmap.facility.dao;

import java.util.List;

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
		Page<FacilityEntity> InfoEntities = facilityRepository.findByEmd(emd, pageable);

		return InfoEntities;
	}

	@Override
	public Page<FacilityEntity> getFacilityBySingleCat(String cat, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntityPage = facilityRepository.findByCat(cat, pageable);

		return facilityEntityPage;
	}

	@Override
	public List<FacilityEntity> getFacilityBySingleEmd(String emd) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntityPage = facilityRepository.findByEmd(emd);

		return facilityEntityPage;
	}

	@Override
	public List<FacilityEntity> getFacilityBySingleCat(String cat) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntityPage = facilityRepository.findByCat(cat);

		return facilityEntityPage;
	}

}
