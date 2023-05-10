package com.pawmap.facility.dao;

import java.util.List;

import com.pawmap.facility.entity.EmdEntity;
import com.pawmap.facility.entity.SidoEntity;
import com.pawmap.facility.entity.SigunguEntity;

public interface DistrictDao {

	List<SidoEntity> getSido();

	List<SigunguEntity> getSigungu(Long sido_id);

	List<EmdEntity> getEmd(Long sigungu_id);

}
