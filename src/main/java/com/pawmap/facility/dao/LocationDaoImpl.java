package com.pawmap.facility.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pawmap.facility.entity.FacilityEntity;
import com.pawmap.facility.repository.LocationRepository;

@Repository
public class LocationDaoImpl implements LocationDao {

	@Autowired
	private LocationRepository locationRepository;
	
	@Override
	public List<FacilityEntity> getLocationBySingleEmd(String emd) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntities = locationRepository.findByEmd(emd);
		
		return facilityEntities;
	}

	@Override
	public List<FacilityEntity> getLocationBySingleCat(String cat) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntities = locationRepository.findByCat(cat);
		
		return facilityEntities;
	}

	@Override
	public List<FacilityEntity> getLocationByGroupSido(String cat, String sido) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntities = locationRepository.findByCatAndSido(cat, sido);
		
		return facilityEntities;
	}

	@Override
	public List<FacilityEntity> getLocationByGroupSigungu(String cat, String sido, String sigungu) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntities = locationRepository.findByCatAndSidoAndSigungu(cat, sido, sigungu);
		
		return facilityEntities;
	}

	@Override
	public List<FacilityEntity> getLocationByGroupEmd(String cat, String sido, String sigungu, String emd) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntities = locationRepository.findByCatAndSidoAndSigunguAndEmd(cat, sido, sigungu, emd);
		
		return facilityEntities;
	}

}