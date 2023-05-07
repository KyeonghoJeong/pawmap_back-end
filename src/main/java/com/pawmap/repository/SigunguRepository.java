package com.pawmap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pawmap.entity.SigunguEntity;

@Repository
public interface SigunguRepository extends JpaRepository<SigunguEntity, Long>{
	
	@Query(value = "SELECT sigungu_id, sigungu_name FROM sigungu WHERE sido_id = :sido_id", nativeQuery = true)
	List<SigunguEntity> findBySido(@Param("sido_id") Long sido_id);

}