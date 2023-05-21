package com.pawmap.facility.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pawmap.facility.dao.ListDao;
import com.pawmap.facility.dto.ListDto;
import com.pawmap.facility.entity.FacilityEntity;

@Service
public class ListServiceImpl implements ListService{

	@Autowired
	private ListDao listDao;

	@Override
	public Page<ListDto> getListBySingleEmd(String emd, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = listDao.getListBySingleEmd(emd, pageable);

		Page<ListDto> listDtos = facilityEntities.map(ListDto::new);
		
		return listDtos;
	}
	
	@Override
	public Page<ListDto> getListBySingleCat(String cat, Double lat, Double lng, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = listDao.getListBySingleCat(cat, lat, lng, pageable);

		Page<ListDto> listDtos = facilityEntities.map(ListDto::new);
		
		return listDtos;
	}

	@Override
	public Page<ListDto> getListByGroupSido(String cat, String sido, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = listDao.getListByGroupSido(cat, sido, pageable);
		
		Page<ListDto> listDtos = facilityEntities.map(ListDto::new);
		
		return listDtos;
	}

	@Override
	public Page<ListDto> getListByGroupSigungu(String cat, String sido, String sigungu, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = listDao.getListByGroupSigungu(cat, sido, sigungu, pageable);
		
		Page<ListDto> listDtos = facilityEntities.map(ListDto::new);
		
		return listDtos;
	}

	@Override
	public Page<ListDto> getListByGroupEmd(String cat, String sido, String sigungu, String emd, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = listDao.getListByGroupEmd(cat, sido, sigungu, emd, pageable);
		
		Page<ListDto> listDtos = facilityEntities.map(ListDto::new);
		
		return listDtos;
	}
	
}
