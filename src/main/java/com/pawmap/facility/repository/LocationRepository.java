package com.pawmap.facility.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawmap.facility.entity.FacilityEntity;

public interface LocationRepository extends JpaRepository<FacilityEntity, Long>{

	List<FacilityEntity> findByEmd(String emd);

	List<FacilityEntity> findByCat(String cat);

}
