package com.pawmap.facility.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pawmap.facility.entity.EmdEntity;
import com.pawmap.facility.entity.SidoEntity;
import com.pawmap.facility.entity.SigunguEntity;
import com.pawmap.facility.repository.EmdRepository;
import com.pawmap.facility.repository.SidoRepository;
import com.pawmap.facility.repository.SigunguRepository;

@Repository
public class DistrictDaoImpl implements DistrictDao {
	
	@Autowired
	private SidoRepository sidoRepository;
	
	@Autowired
	private SigunguRepository sigunguRepository;
	
	@Autowired
	private EmdRepository emdRepository;

	@Override
	public List<SidoEntity> getSido() {
		// TODO Auto-generated method stub
		List<SidoEntity> sidoEntity = sidoRepository.findAll();
		
		return sidoEntity;
	}

	@Override
	public List<SigunguEntity> getSigungu(Long sidoId) {
		// TODO Auto-generated method stub
		List<SigunguEntity> sigunguEntity = sigunguRepository.findBySidoId(sidoId);
		
		return sigunguEntity;
	}

	@Override
	public List<EmdEntity> getEmd(Long sigunguId) {
		// TODO Auto-generated method stub
		List<EmdEntity> emdEntity = emdRepository.findBySigunguId(sigunguId);
		
		return emdEntity;
	}
	
}
