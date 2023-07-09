package com.pawmap.map.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pawmap.map.entity.FacilityEntity;

@Repository
public interface FacilityRepository extends JpaRepository<FacilityEntity, Long> {
	
	// Facility
	
	// LIKE를 써서 정확히 일치하지 않아도 검색한 동 이름을 포함하는 경우 모두 조회
	@Query(value="SELECT * FROM facility WHERE emd LIKE %:emd% ORDER BY sido, sigungu, cat", nativeQuery=true)
	Page<FacilityEntity> getFacilitiesByEmd(@Param("emd") String emd, Pageable pageable);
	
	// lat, lng 파라미터는 현재 위치의 위도, 경도
	// ORDER 문의 lat, lng이 WHERE 절에 없을 경우 오류가 발생해서 결과에 영향을 끼치지 않는 식으로 포함시킴 => (lat >= :lat OR lat <= :lat) AND (lng >= :lng OR lng <= :lng)
	// POWER(:lat - lat, 2) + POWER(:lng - lng, 2) => 피타고라스 정리를 이용해서 현재 위치(기준)과 가까운 순으로 정렬
	@Query(value="SELECT * FROM facility WHERE cat LIKE %:cat% AND (lat >= :lat OR lat <= :lat) AND (lng >= :lng OR lng <= :lng) ORDER BY POWER(:lat - lat, 2) + POWER(:lng - lng, 2)", nativeQuery=true)
	Page<FacilityEntity> getFacilitiesByCat(@Param("cat") String cat, @Param("lat") Double lat, @Param("lng") Double lng, Pageable pageable);

	// cat, sido로 검색한 뒤 sigungu, emd 순으로 정렬
	Page<FacilityEntity> findByCatAndSidoOrderBySigunguAscEmdAsc(String cat, String sido, Pageable pageable);

	// cat, sido, sigungu로 검색한 뒤 emd 순으로 정렬
	Page<FacilityEntity> findByCatAndSidoAndSigunguOrderByEmd(String cat, String sido, String sigungu, Pageable pageable);

	// cat, sido, sigungu, emd로 검색
	Page<FacilityEntity> findByCatAndSidoAndSigunguAndEmd(String cat, String sido, String sigungu, String emd, Pageable pageable);
	
	// FacilityLocation
	
	// LIKE를 써서 정확히 일치하지 않아도 검색한 동 이름을 포함하는 경우 모두 조회
	@Query(value="SELECT * FROM facility WHERE emd LIKE %:emd%", nativeQuery=true)
	List<FacilityEntity> getFacilityLocationsByEmd(@Param("emd") String emd);

	// LIKE를 써서 정확히 일치하지 않아도 선택한 카테고리를 포함하는 경우 모두 조회
	@Query(value="SELECT * FROM facility WHERE cat LIKE %:cat%", nativeQuery=true)
	List<FacilityEntity> getFacilityLocationsByCat(@Param("cat") String cat);

	// cat, sido로 조회
	List<FacilityEntity> findByCatAndSido(String cat, String sido);

	// cat, sido, sigungu로 조회
	List<FacilityEntity> findByCatAndSidoAndSigungu(String cat, String sido, String sigungu);

	// cat, sido, sigungu, emd으로 조회
	List<FacilityEntity> findByCatAndSidoAndSigunguAndEmd(String cat, String sido, String sigungu, String emd);

	// Bookmark
	
	// 서브 쿼리로 북마크 테이블에서 memberId로 조회하여 facilityId를 가져오고 시설 테이블에서 가져온 id로 조회
	@Query(value="SELECT * FROM facility WHERE facilityId IN (SELECT facilityId FROM bookmark WHERE memberId = :memberid) ORDER BY cat, roadaddr", nativeQuery=true)
	Page<FacilityEntity> getBookmarks(@Param("memberid") String memberId, Pageable pageable);

}
