package com.pawmap.facility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pawmap.facility.entity.FacilityEntity;

public interface AvailabilityRepository extends JpaRepository<FacilityEntity, Long>{

	@Query(value="SELECT COUNT(*) FROM facility WHERE emd LIKE %:emd%", nativeQuery=true)
	Long countByEmd(@Param("emd") String emd);

	Long countByCatAndSido(String cat, String sido);

	Long countByCatAndSidoAndSigungu(String cat, String sido, String sigungu);

	Long countByCatAndSidoAndSigunguAndEmd(String cat, String sido, String sigungu, String emd);
	
}
