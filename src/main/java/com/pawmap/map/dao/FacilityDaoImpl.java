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
		
		// �Ķ���ͷ� ���� �ü� id�� row ��������
		Optional<FacilityEntity> optionalFacilityEntity = facilityRepository.findById(facilityId);
		
		FacilityEntity facilityEntity = null;
		
		// null�� �ƴ� ��� �� ��������
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
		
		// �� �Ķ���� �� null�� �ƴ� ������ �������� �ü� ��� ��ȸ
		
		// �� �̸� �˻�
		if(cat == null && sido == null && sigungu == null && emd != null) {
			facilityEntities = facilityRepository.getFacilityLocationsByEmd(emd);
		}
		
		// ī�װ� ī�� Ŭ�� �Ǵ� ī�װ� select
		if(cat != null && sido == null && sigungu == null && emd == null) {
			facilityEntities = facilityRepository.getFacilityLocationsByCat(cat);
		}
		
		// ī�װ� + �õ�
		if(cat != null && sido != null && sigungu == null && emd == null) {
			facilityEntities = facilityRepository.findByCatAndSido(cat, sido);
		}
		
		// ī�װ� + �õ� + �ñ���
		if(cat != null && sido != null && sigungu != null && emd == null) {
			facilityEntities = facilityRepository.findByCatAndSidoAndSigungu(cat, sido, sigungu);
		}
		
		// ī�װ� + �õ� + �ñ��� + ���鵿
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
		
		// �� �Ķ���� �� null�� �ƴ� ������ �������� �ü� ��� ��ȸ
		
		// �� �̸� �˻�
		if(cat == null && sido == null && sigungu == null && emd != null) {
			facilityEntities = facilityRepository.getFacilitiesByEmd(emd, pageable);
		}
		
		// ī�װ� ī�� Ŭ�� �Ǵ� ī�װ� select
		if(cat != null && sido == null && sigungu == null && emd == null) {
			facilityEntities = facilityRepository.getFacilitiesByCat(cat, lat, lng, pageable);
		}
		
		// ī�װ� + �õ�
		if(cat != null && sido != null && sigungu == null && emd == null) {
			facilityEntities = facilityRepository.findByCatAndSidoOrderBySigunguAscEmdAsc(cat, sido, pageable);
		}
		
		// ī�װ� + �õ� + �ñ���
		if(cat != null && sido != null && sigungu != null && emd == null) {
			facilityEntities = facilityRepository.findByCatAndSidoAndSigunguOrderByEmd(cat, sido, sigungu, pageable);
		}
		
		// ī�װ� + �õ� + �ñ��� + ���鵿
		if(cat != null && sido != null && sigungu != null && emd != null) {
			facilityEntities = facilityRepository.findByCatAndSidoAndSigunguAndEmd(cat, sido, sigungu, emd, pageable);
		}
		
		return facilityEntities;
	}

}
