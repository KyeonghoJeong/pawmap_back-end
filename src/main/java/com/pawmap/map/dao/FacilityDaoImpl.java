package com.pawmap.map.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.pawmap.map.entity.FacilityEntity;
import com.pawmap.map.repository.FacilityRepository;

@Repository
public class FacilityDaoImpl implements FacilityDao {
	
	@Autowired
	private FacilityRepository facilityRepository;
	
	@Override
	public FacilityEntity getFacilityInformation(Long facilityId) {
		// TODO Auto-generated method stub
		
		// 파라미터로 받은 시설 id로 row 가져오기
		Optional<FacilityEntity> optionalFacilityEntity = facilityRepository.findById(facilityId);
		
		FacilityEntity facilityEntity = null;
		
		// null이 아닌 경우 값 가져오기
		if(optionalFacilityEntity != null) {
			facilityEntity = optionalFacilityEntity.get();
		}
		
		return facilityEntity;
	}
	
	@Override
	public List<FacilityEntity> getFacilityLocations(String cat, String sido, String sigungu, String emd, Double lat,
			Double lng) {
		// TODO Auto-generated method stub
		List<FacilityEntity> facilityEntities = null;
		
		// 각 파라미터 중 null이 아닌 값들의 조합으로 시설 목록 조회
		
		// 동 이름 검색
		if(cat == null && sido == null && sigungu == null && emd != null) {
			facilityEntities = facilityRepository.getFacilityLocationsByEmd(emd);
		}
		
		// 카테고리 카드 클릭 또는 카테고리 select
		if(cat != null && sido == null && sigungu == null && emd == null) {
			facilityEntities = facilityRepository.getFacilityLocationsByCat(cat);
		}
		
		// 카테고리 + 시도
		if(cat != null && sido != null && sigungu == null && emd == null) {
			facilityEntities = facilityRepository.findByCatAndSido(cat, sido);
		}
		
		// 카테고리 + 시도 + 시군구
		if(cat != null && sido != null && sigungu != null && emd == null) {
			facilityEntities = facilityRepository.findByCatAndSidoAndSigungu(cat, sido, sigungu);
		}
		
		// 카테고리 + 시도 + 시군구 + 읍면동
		if(cat != null && sido != null && sigungu != null && emd != null) {
			facilityEntities = facilityRepository.findByCatAndSidoAndSigunguAndEmd(cat, sido, sigungu, emd);
		}
				
		return facilityEntities;
	}

	@Override
	public Page<FacilityEntity> getFacilities(String cat, String sido, String sigungu, String emd, Double lat,
			Double lng, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = null;
		
		// 각 파라미터 중 null이 아닌 값들의 조합으로 시설 목록 조회
		
		// 동 이름 검색
		if(cat == null && sido == null && sigungu == null && emd != null) {
			facilityEntities = facilityRepository.getFacilitiesByEmd(emd, pageable);
		}
		
		// 카테고리 카드 클릭 또는 카테고리 select
		if(cat != null && sido == null && sigungu == null && emd == null) {
			facilityEntities = facilityRepository.getFacilitiesByCat(cat, lat, lng, pageable);
		}
		
		// 카테고리 + 시도
		if(cat != null && sido != null && sigungu == null && emd == null) {
			facilityEntities = facilityRepository.findByCatAndSidoOrderBySigunguAscEmdAsc(cat, sido, pageable);
		}
		
		// 카테고리 + 시도 + 시군구
		if(cat != null && sido != null && sigungu != null && emd == null) {
			facilityEntities = facilityRepository.findByCatAndSidoAndSigunguOrderByEmd(cat, sido, sigungu, pageable);
		}
		
		// 카테고리 + 시도 + 시군구 + 읍면동
		if(cat != null && sido != null && sigungu != null && emd != null) {
			facilityEntities = facilityRepository.findByCatAndSidoAndSigunguAndEmd(cat, sido, sigungu, emd, pageable);
		}
		
		return facilityEntities;
	}

}
