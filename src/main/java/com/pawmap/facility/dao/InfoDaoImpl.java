package com.pawmap.facility.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.pawmap.facility.entity.FacilityEntity;
import com.pawmap.facility.repository.InfoRepository;

@Repository
public class InfoDaoImpl implements InfoDao {
	
	@Autowired
	private InfoRepository infoRepository;
	
	@Override
	public Page<FacilityEntity> getInfoBySingleEmd(String emd, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = infoRepository.findByEmdOrderBySidoAscSigunguAscCatAsc(emd, pageable);

		return facilityEntities;
	}

	@Override
	public Page<FacilityEntity> getInfoBySingleCat(String cat, Double lat, Double lng, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = infoRepository.getInfoBySingleCat(cat, lat, lng, pageable);

		return facilityEntities;
	}

	@Override
	public Page<FacilityEntity> getInfoByGroupSido(String cat, String sido, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = infoRepository.findByCatAndSidoOrderBySigunguAscEmdAsc(cat, sido, pageable);

		return facilityEntities;
	}

	@Override
	public Page<FacilityEntity> getInfoByGroupSigungu(String cat, String sido, String sigungu, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = infoRepository.findByCatAndSidoAndSigunguOrderByEmd(cat, sido, sigungu, pageable);

		return facilityEntities;
	}

	@Override
	public Page<FacilityEntity> getInfoByGroupEmd(String cat, String sido, String sigungu, String emd, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = infoRepository.findByCatAndSidoAndSigunguAndEmd(cat, sido, sigungu, emd, pageable);

		return facilityEntities;
	}

}
