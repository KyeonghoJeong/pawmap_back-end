package com.pawmap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pawmap.entity.FacilityEntity;

@Repository
public interface FacilityRepository extends JpaRepository<FacilityEntity, Long> {

	List<FacilityEntity> findByEmd(String emd);
	List<FacilityEntity> findByCat(String cat);
	
}