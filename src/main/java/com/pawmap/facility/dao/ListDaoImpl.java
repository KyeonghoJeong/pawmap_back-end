package com.pawmap.facility.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.pawmap.facility.entity.FacilityEntity;
import com.pawmap.facility.repository.ListRepository;

@Repository
public class ListDaoImpl implements ListDao {
	
	@Autowired
	private ListRepository listRepository;
	
	@Override
	public Page<FacilityEntity> getListBySingleEmd(String emd, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = listRepository.findByEmdOrderBySidoAscSigunguAscCatAsc(emd, pageable);

		return facilityEntities;
	}

	@Override
	public Page<FacilityEntity> getListBySingleCat(String cat, Double lat, Double lng, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = listRepository.getListBySingleCat(cat, lat, lng, pageable);

		return facilityEntities;
	}

	@Override
	public Page<FacilityEntity> getListByGroupSido(String cat, String sido, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = listRepository.findByCatAndSidoOrderBySigunguAscEmdAsc(cat, sido, pageable);

		return facilityEntities;
	}

	@Override
	public Page<FacilityEntity> getListByGroupSigungu(String cat, String sido, String sigungu, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = listRepository.findByCatAndSidoAndSigunguOrderByEmd(cat, sido, sigungu, pageable);

		return facilityEntities;
	}

	@Override
	public Page<FacilityEntity> getListByGroupEmd(String cat, String sido, String sigungu, String emd, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = listRepository.findByCatAndSidoAndSigunguAndEmd(cat, sido, sigungu, emd, pageable);

		return facilityEntities;
	}

}
