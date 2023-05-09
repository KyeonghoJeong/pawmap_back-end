package com.pawmap.map.dao;

import java.util.List;

import com.pawmap.map.entity.EmdEntity;
import com.pawmap.map.entity.SidoEntity;
import com.pawmap.map.entity.SigunguEntity;

public interface DistrictDao {

	List<SidoEntity> getSido();

	List<SigunguEntity> getSigungu(Long sido_id);

	List<EmdEntity> getEmd(Long sigungu_id);

}
