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
	
	// LIKE�� �Ἥ ��Ȯ�� ��ġ���� �ʾƵ� �˻��� �� �̸��� �����ϴ� ��� ��� ��ȸ
	@Query(value="SELECT * FROM facility WHERE emd LIKE %:emd% ORDER BY sido, sigungu, cat", nativeQuery=true)
	Page<FacilityEntity> getFacilitiesByEmd(@Param("emd") String emd, Pageable pageable);
	
	// lat, lng �Ķ���ʹ� ���� ��ġ�� ����, �浵
	// ORDER ���� lat, lng�� WHERE ���� ���� ��� ������ �߻��ؼ� ����� ������ ��ġ�� �ʴ� ������ ���Խ�Ŵ => (lat >= :lat OR lat <= :lat) AND (lng >= :lng OR lng <= :lng)
	// POWER(:lat - lat, 2) + POWER(:lng - lng, 2) => ��Ÿ��� ������ �̿��ؼ� ���� ��ġ(����)�� ����� ������ ����
	@Query(value="SELECT * FROM facility WHERE cat LIKE %:cat% AND (lat >= :lat OR lat <= :lat) AND (lng >= :lng OR lng <= :lng) ORDER BY POWER(:lat - lat, 2) + POWER(:lng - lng, 2)", nativeQuery=true)
	Page<FacilityEntity> getFacilitiesByCat(@Param("cat") String cat, @Param("lat") Double lat, @Param("lng") Double lng, Pageable pageable);

	// cat, sido�� �˻��� �� sigungu, emd ������ ����
	Page<FacilityEntity> findByCatAndSidoOrderBySigunguAscEmdAsc(String cat, String sido, Pageable pageable);

	// cat, sido, sigungu�� �˻��� �� emd ������ ����
	Page<FacilityEntity> findByCatAndSidoAndSigunguOrderByEmd(String cat, String sido, String sigungu, Pageable pageable);

	// cat, sido, sigungu, emd�� �˻�
	Page<FacilityEntity> findByCatAndSidoAndSigunguAndEmd(String cat, String sido, String sigungu, String emd, Pageable pageable);
	
	// FacilityLocation
	
	// LIKE�� �Ἥ ��Ȯ�� ��ġ���� �ʾƵ� �˻��� �� �̸��� �����ϴ� ��� ��� ��ȸ
	@Query(value="SELECT * FROM facility WHERE emd LIKE %:emd%", nativeQuery=true)
	List<FacilityEntity> getFacilityLocationsByEmd(@Param("emd") String emd);

	// LIKE�� �Ἥ ��Ȯ�� ��ġ���� �ʾƵ� ������ ī�װ��� �����ϴ� ��� ��� ��ȸ
	@Query(value="SELECT * FROM facility WHERE cat LIKE %:cat%", nativeQuery=true)
	List<FacilityEntity> getFacilityLocationsByCat(@Param("cat") String cat);

	// cat, sido�� ��ȸ
	List<FacilityEntity> findByCatAndSido(String cat, String sido);

	// cat, sido, sigungu�� ��ȸ
	List<FacilityEntity> findByCatAndSidoAndSigungu(String cat, String sido, String sigungu);

	// cat, sido, sigungu, emd���� ��ȸ
	List<FacilityEntity> findByCatAndSidoAndSigunguAndEmd(String cat, String sido, String sigungu, String emd);

	// Bookmark
	
	// ���� ������ �ϸ�ũ ���̺��� memberId�� ��ȸ�Ͽ� facilityId�� �������� �ü� ���̺��� ������ id�� ��ȸ
	@Query(value="SELECT * FROM facility WHERE facilityId IN (SELECT facilityId FROM bookmark WHERE memberId = :memberid) ORDER BY cat, roadaddr", nativeQuery=true)
	Page<FacilityEntity> getBookmarks(@Param("memberid") String memberId, Pageable pageable);

}
