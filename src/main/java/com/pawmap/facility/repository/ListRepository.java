package com.pawmap.facility.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pawmap.facility.entity.FacilityEntity;

@Repository
public interface ListRepository extends JpaRepository<FacilityEntity, Long> {

	@Query(value="SELECT * FROM facility WHERE emd LIKE %:emd% ORDER BY sido, sigungu, cat", nativeQuery=true)
	Page<FacilityEntity> findByEmdOrderBySidoAscSigunguAscCatAsc(@Param("emd") String emd, Pageable pageable);
	
	@Query(value="SELECT * FROM facility WHERE cat LIKE %:cat% AND (lat >= :lat OR lat <= :lat) AND (lng >= :lng OR lng <= :lng) ORDER BY POWER(:lat - lat, 2) + POWER(:lng - lng, 2)", nativeQuery=true)
	Page<FacilityEntity> getListBySingleCat(@Param("cat") String cat, @Param("lat") Double lat, @Param("lng") Double lng, Pageable pageable);

	Page<FacilityEntity> findByCatAndSidoOrderBySigunguAscEmdAsc(String cat, String sido, Pageable pageable);

	Page<FacilityEntity> findByCatAndSidoAndSigunguOrderByEmd(String cat, String sido, String sigungu, Pageable pageable);

	Page<FacilityEntity> findByCatAndSidoAndSigunguAndEmd(String cat, String sido, String sigungu, String emd, Pageable pageable);
	
}