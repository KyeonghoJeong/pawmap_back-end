package com.pawmap.facility.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pawmap.facility.entity.FacilityEntity;

public interface LocationRepository extends JpaRepository<FacilityEntity, Long>{

	@Query(value="SELECT * FROM facility WHERE emd LIKE %:emd%", nativeQuery=true)
	List<FacilityEntity> findByEmd(@Param("emd") String emd);

	@Query(value="SELECT * FROM facility WHERE cat LIKE %:cat%", nativeQuery=true)
	List<FacilityEntity> findByCat(@Param("cat") String cat);

	List<FacilityEntity> findByCatAndSido(String cat, String sido);

	List<FacilityEntity> findByCatAndSidoAndSigungu(String cat, String sido, String sigungu);

	List<FacilityEntity> findByCatAndSidoAndSigunguAndEmd(String cat, String sido, String sigungu, String emd);

}