package com.pawmap.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pawmap.map.entity.BookmarkEntity;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long>{
	
	Long countByMemberIdAndFacilityId(String memberId, Long facilityId);
	
	void deleteByFacilityIdAndMemberId(Long facilityId, String memberId);

}
