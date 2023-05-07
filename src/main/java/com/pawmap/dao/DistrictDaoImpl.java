package com.pawmap.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pawmap.entity.EmdEntity;
import com.pawmap.entity.SidoEntity;
import com.pawmap.entity.SigunguEntity;
import com.pawmap.repository.EmdRepository;
import com.pawmap.repository.SidoRepository;
import com.pawmap.repository.SigunguRepository;

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
	public List<SigunguEntity> getSigungu(Long sido_id) {
		// TODO Auto-generated method stub
		List<SigunguEntity> sigunguEntity = sigunguRepository.findBySido(sido_id);
		
		return sigunguEntity;
	}

	@Override
	public List<EmdEntity> getEmd(Long sigungu_id) {
		// TODO Auto-generated method stub
		List<EmdEntity> emdEntity = emdRepository.findBySigungu(sigungu_id);
		
		return emdEntity;
	}
	
}
