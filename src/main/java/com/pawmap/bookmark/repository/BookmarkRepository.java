package com.pawmap.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawmap.bookmark.entity.BookmarkEntity;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long>{

	Long countByMemberIdAndFacilityId(String memberId, Long facilityId);

}