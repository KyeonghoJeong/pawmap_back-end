package com.pawmap.bookmark.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.bookmark.entity.BookmarkEntity;
import com.pawmap.facility.entity.FacilityEntity;

public interface BookmarkDao {

	String addBookmark(BookmarkEntity bookmarkEntity);

	Page<FacilityEntity> getBookmarkInfo(String memberId, Pageable pageable);

	void deleteBookmark(String memberId, List<Long> facilityId);

}
