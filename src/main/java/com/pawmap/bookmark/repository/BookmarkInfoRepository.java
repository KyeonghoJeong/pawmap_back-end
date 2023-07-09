package com.pawmap.bookmark.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pawmap.map.entity.FacilityEntity;

@Repository
public interface BookmarkInfoRepository extends JpaRepository<FacilityEntity, Long>{

	@Query(value="SELECT\r\n"
			+ "    a.facilityId,\r\n"
			+ "    a.sido,\r\n"
			+ "    a.sigungu,\r\n"
			+ "    a.emd,\r\n"
			+ "    a.cat,\r\n"
			+ "    a.facilityName,\r\n"
			+ "    a.basicInfo,\r\n"
			+ "    a.roadAddr,\r\n"
			+ "    a.landAddr,\r\n"
			+ "    a.phoneNum,\r\n"
			+ "    a.website,\r\n"
			+ "    a.closedDay,\r\n"
			+ "    a.businessHr,\r\n"
			+ "    a.parkingAvail,\r\n"
			+ "    a.admissionFee,\r\n"
			+ "    a.petFee,\r\n"
			+ "    a.petExclusive,\r\n"
			+ "    a.petSize,\r\n"
			+ "    a.petRestrictions,\r\n"
			+ "    a.indoorAvail,\r\n"
			+ "    a.outdoorAvail,\r\n"
			+ "    a.lat,\r\n"
			+ "    a.lng\r\n"
			+ "FROM facility a JOIN (SELECT * FROM bookmark WHERE memberId = :memberId) b ON a.facilityId = b.facilityId ORDER BY a.cat", nativeQuery=true)
	Page<FacilityEntity> getInfo(@Param("memberId") String memberId, Pageable pageable);

}