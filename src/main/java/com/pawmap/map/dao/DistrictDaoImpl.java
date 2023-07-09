package com.pawmap.map.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pawmap.map.entity.EmdEntity;
import com.pawmap.map.entity.SidoEntity;
import com.pawmap.map.entity.SigunguEntity;
import com.pawmap.map.repository.EmdRepository;
import com.pawmap.map.repository.SidoRepository;
import com.pawmap.map.repository.SigunguRepository;

@Repository
public class DistrictDaoImpl implements DistrictDao {
	
	@Autowired
	private SidoRepository sidoRepository;
	
	@Autowired
	private SigunguRepository sigunguRepository;
	
	@Autowired
	private EmdRepository emdRepository;

	@Override
	public List<SidoEntity> getSidos() {
		// TODO Auto-generated method stub
		List<SidoEntity> sidoEntities = sidoRepository.findAll();
		
		return sidoEntities;
	}

	@Override
	public List<SigunguEntity> getSigungus(Long sidoId) {
		// TODO Auto-generated method stub
		List<SigunguEntity> sigunguEntities = sigunguRepository.findBySidoId(sidoId);
		
		return sigunguEntities;
	}

	@Override
	public List<EmdEntity> getEmds(Long sigunguId) {
		// TODO Auto-generated method stub
		List<EmdEntity> emdEntities = emdRepository.findBySigunguId(sigunguId);
		
		return emdEntities;
	}
	
}
