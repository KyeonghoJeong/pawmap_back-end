package com.pawmap.map.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.map.dto.BookmarkFacilityDto;

public interface BookmarkService {

	String postBookmark(Map<String, Long> facilityData);

	Page<BookmarkFacilityDto> getBookmarks(String memberId, Pageable pageable);
	
	void deleteBookmarks(List<Long> facilityIds, String memberId);

}
