package com.pawmap.facility.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pawmap.facility.entity.FacilityEntity;

@Repository
public interface FacilityRepository extends JpaRepository<FacilityEntity, Long> {

	@Query(value = "SELECT * "
			+ "FROM facility "
			+ "WHERE emd = :emd "
			+ "ORDER BY POWER((SELECT lat FROM facility WHERE emd = :emd and rownum = 1) - lat, 2) "
			+ "+ POWER((SELECT lng FROM facility WHERE emd = :emd and rownum = 1) - lng, 2)"
			, nativeQuery=true)
	Page<FacilityEntity> findByEmd(@Param("emd") String emd, Pageable pageable);

	Page<FacilityEntity> findByCat(String cat, Pageable pageable);

	List<FacilityEntity> findByEmd(String emd);
	
	List<FacilityEntity> findByCat(String cat);

}