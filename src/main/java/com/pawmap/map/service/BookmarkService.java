package com.pawmap.map.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.map.dto.BookmarkFacilityDto;

public interface BookmarkService {

	String postBookmark(Long facilityId);

	Page<BookmarkFacilityDto> getBookmarks(String memberId, Pageable pageable);
	
	void deleteBookmarks(String memberId, List<Long> facilityIds);

}
