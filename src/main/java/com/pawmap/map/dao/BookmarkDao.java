package com.pawmap.map.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.map.entity.BookmarkEntity;
import com.pawmap.map.entity.FacilityEntity;

public interface BookmarkDao {

	String postBookmark(BookmarkEntity bookmarkEntity);
	
	Page<FacilityEntity> getBookmarks(String memberId, Pageable pageable);

	void deleteBookmarks(List<Long> facilityIds, String memberId);

}
