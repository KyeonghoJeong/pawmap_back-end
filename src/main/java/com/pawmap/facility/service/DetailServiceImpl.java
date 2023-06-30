package com.pawmap.facility.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawmap.facility.dao.DetailDao;
import com.pawmap.facility.dto.DetailDto;
import com.pawmap.facility.entity.FacilityEntity;

@Service
public class DetailServiceImpl implements DetailService {

	@Autowired
	private DetailDao detailDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public DetailDto getFacility(Long facilityId) {
		// TODO Auto-generated method stub
		FacilityEntity facilityEntity = detailDao.getFacility(facilityId); // ��ƼƼ �ޱ�
		
		DetailDto detailDto = modelMapper.map(facilityEntity, DetailDto.class); // ��ƼƼ => dto ����
		
		return detailDto;
	}

}
