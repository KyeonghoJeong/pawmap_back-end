package com.pawmap.bookmark.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.bookmark.dto.BookmarkDto;
import com.pawmap.bookmark.dto.BookmarkInfoDto;

public interface BookmarkService {

	String addBookmark(BookmarkDto addingBookmarkDto);

	Page<BookmarkInfoDto> getBookmarkInfo(String memberId, Pageable pageable);

	void deleteBookmark(String memberId, List<Long> facilityId);

}