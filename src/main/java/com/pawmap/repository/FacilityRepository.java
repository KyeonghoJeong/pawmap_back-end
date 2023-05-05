package com.pawmap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pawmap.entity.FacilityEntity;

@Repository
public interface FacilityRepository extends JpaRepository<FacilityEntity, Long> {

	@Query(value = "SELECT facility_id, facility_name, basic_info, road_addr, lat, lng from facility WHERE emd = :emd", nativeQuery = true)
	List<FacilityEntity> findByEmd(@Param("emd") String emd);
	
	@Query(value = "SELECT facility_id, facility_name, basic_info, road_addr, lat, lng from facility WHERE cat = :cat", nativeQuery = true)
	List<FacilityEntity> findByCat(@Param("cat") String cat);
	
}