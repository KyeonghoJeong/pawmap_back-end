package com.pawmap.facility.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pawmap.facility.entity.FacilityEntity;

@Repository
public interface InfoRepository extends JpaRepository<FacilityEntity, Long> {

	@Query(value = "SELECT * FROM facility WHERE cat = :cat AND (lat >= :lat OR lat <= :lat) AND (lng >= :lng OR lng <= :lng) ORDER BY POWER(:lat - lat, 2) + POWER(:lng - lng, 2)", nativeQuery=true)
	Page<FacilityEntity> getInfoBySingleCat(@Param("cat") String cat, @Param("lat") Double lat, @Param("lng") Double lng, Pageable pageable);

	Page<FacilityEntity> findByEmdOrderBySigungu(String emd, Pageable pageable);

	Long countByEmd(String emd);
	
}