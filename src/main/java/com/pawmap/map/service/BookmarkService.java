package com.pawmap.map.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.map.dto.BookmarkDto;
import com.pawmap.map.dto.BookmarkFacilityDto;

public interface BookmarkService {

	boolean postBookmark(BookmarkDto bookmarkDto);

	Page<BookmarkFacilityDto> getBookmarks(Pageable pageable);
	
	void deleteBookmarks(List<Long> facilityIds);

}
