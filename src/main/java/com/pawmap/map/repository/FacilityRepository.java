package com.pawmap.map.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pawmap.map.entity.FacilityEntity;

@Repository
public interface FacilityRepository extends JpaRepository<FacilityEntity, Long> {

	Page<FacilityEntity> findByEmd(String emd, Pageable pageable);

	Page<FacilityEntity> findByCat(String cat, Pageable pageable);

	List<FacilityEntity> findByCat(String cat);

	List<FacilityEntity> findByEmd(String emd);
	
}