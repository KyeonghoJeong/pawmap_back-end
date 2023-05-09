package com.pawmap.map.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawmap.map.entity.EmdEntity;

public interface EmdRepository extends JpaRepository<EmdEntity, Long>{

	List<EmdEntity> findBySigunguId(Long sigunguId);
	
}
