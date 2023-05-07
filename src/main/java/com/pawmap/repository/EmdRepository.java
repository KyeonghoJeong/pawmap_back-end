package com.pawmap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pawmap.entity.EmdEntity;

public interface EmdRepository extends JpaRepository<EmdEntity, Long>{

	@Query(value = "SELECT emd_id, emd_name FROM emd WHERE sigungu_id = :sigungu_id", nativeQuery = true)
	List<EmdEntity> findBySigungu(@Param("sigungu_id") Long sigungu_id);
	
}
