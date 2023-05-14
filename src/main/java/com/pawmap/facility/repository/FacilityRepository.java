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

	// ORDER BY 절에 사용한 파라미터를 WHERE 절에도 모두 사용하지 않으면 에러 발생
	//@Query(value = "SELECT * FROM facility WHERE cat = :cat and lat > :lat ORDER BY POWER((:lat) - lat, 2) + POWER((:lng) - lng, 2)", nativeQuery=true)
	@Query(value = "SELECT * "
			+ "FROM facility "
			+ "WHERE cat = :cat AND (lat >= :lat OR lat <= :lat) AND (lng >= :lng OR lng <= :lng) "
			+ "ORDER BY POWER(:lat - lat, 2) + POWER(:lng - lng, 2)", nativeQuery=true)
	Page<FacilityEntity> findByCat(@Param("cat") String cat, @Param("lat") Double lat, @Param("lng") Double lng, Pageable pageable);
	
	// ORDER BY 절에 사용한 파라미터를 WHERE 절에도 모두 사용하지 않으면 에러 발생
	// @Query(value = "SELECT * FROM facility WHERE cat = '동물병원' ORDER BY POWER(?1 - lat, 2) + POWER(?2 - lng, 2)", nativeQuery=true)
	@Query(value = "SELECT * "
			+ "FROM facility "
			+ "WHERE cat = '동물병원' AND (lat >= :lat OR lat <= :lat) AND (lng >= :lng OR lng <= :lng) "
			+ "ORDER BY POWER(:lat - lat, 2) + POWER(:lng - lng, 2)", nativeQuery=true)
	Page<FacilityEntity> findAll(@Param("lat") Double lat, @Param("lng") Double lng, Pageable pageable);

	List<FacilityEntity> findByEmd(String emd);
	
	List<FacilityEntity> findByCat(String cat);

	@Query(value ="SELECT * FROM facility WHERE cat = '동물병원'", nativeQuery=true)
	List<FacilityEntity> findByLocation();
	
}