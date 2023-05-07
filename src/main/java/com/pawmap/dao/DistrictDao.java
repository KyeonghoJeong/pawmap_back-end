package com.pawmap.dao;

import java.util.List;

import com.pawmap.entity.EmdEntity;
import com.pawmap.entity.SidoEntity;
import com.pawmap.entity.SigunguEntity;

public interface DistrictDao {

	List<SidoEntity> getSido();

	List<SigunguEntity> getSigungu(Long sido_id);

	List<EmdEntity> getEmd(Long sigungu_id);

}
