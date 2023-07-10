package com.pawmap.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pawmap.map.entity.BookmarkEntity;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long>{
	
	// 회원 아이디 + 시설 id로 조회하여 일치하는 row 수 리턴
	Long countByMemberIdAndFacilityId(String memberId, Long facilityId);
	
	// 시설 id + 회원 아이디로 일치하는 북마크 row 삭제 
	void deleteByFacilityIdAndMemberId(Long facilityId, String memberId);

	// 회원 아이디로 조회하여 해당하는 모든 북마크 삭제
	void deleteAllByMemberId(String memberId);

}
