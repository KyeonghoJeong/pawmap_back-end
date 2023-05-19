package com.pawmap.facility.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pawmap.facility.dao.InfoDao;
import com.pawmap.facility.dto.InfoDto;
import com.pawmap.facility.entity.FacilityEntity;

@Service
public class InfoServiceImpl implements InfoService{

	@Autowired
	private InfoDao infoDao;

	@Override
	public Page<InfoDto> getInfoBySingleEmd(String emd, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = infoDao.getInfoBySingleEmd(emd, pageable);

		Page<InfoDto> infoDtos = facilityEntities.map(InfoDto::new);
		
		return infoDtos;
	}
	
	@Override
	public Page<InfoDto> getInfoBySingleCat(String cat, Double lat, Double lng, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = infoDao.getInfoBySingleCat(cat, lat, lng, pageable);

		Page<InfoDto> infoDtos = facilityEntities.map(InfoDto::new);
		
		return infoDtos;
	}

	@Override
	public Page<InfoDto> getInfoByGroupSido(String cat, String sido, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = infoDao.getInfoByGroupSido(cat, sido, pageable);
		
		Page<InfoDto> infoDtos = facilityEntities.map(InfoDto::new);
		
		return infoDtos;
	}

	@Override
	public Page<InfoDto> getInfoByGroupSigungu(String cat, String sido, String sigungu, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = infoDao.getInfoByGroupSigungu(cat, sido, sigungu, pageable);
		
		Page<InfoDto> infoDtos = facilityEntities.map(InfoDto::new);
		
		return infoDtos;
	}

	@Override
	public Page<InfoDto> getInfoByGroupEmd(String cat, String sido, String sigungu, String emd, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = infoDao.getInfoByGroupEmd(cat, sido, sigungu, emd, pageable);
		
		Page<InfoDto> infoDtos = facilityEntities.map(InfoDto::new);
		
		return infoDtos;
	}
	
}
