package com.pawmap.bookmark.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pawmap.bookmark.dao.BookmarkDao;
import com.pawmap.bookmark.dto.BookmarkDto;
import com.pawmap.bookmark.dto.BookmarkInfoDto;
import com.pawmap.bookmark.entity.BookmarkEntity;
import com.pawmap.facility.entity.FacilityEntity;

@Service
public class BookmarkServiceImpl implements BookmarkService {
	
	@Autowired
	private BookmarkDao bookmarkDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String addBookmark(BookmarkDto bookmarkDto) {
		// TODO Auto-generated method stub
		//modelMapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE);
		BookmarkEntity bookmarkEntity = modelMapper.map(bookmarkDto, BookmarkEntity.class);
		
		String result = bookmarkDao.addBookmark(bookmarkEntity);
		
		return result;
	}

	@Override
	public Page<BookmarkInfoDto> getBookmarkInfo(String memberId, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = bookmarkDao.getBookmarkInfo(memberId, pageable);
		
		Page<BookmarkInfoDto> bookmarkInfoDto = facilityEntities.map(BookmarkInfoDto::new);
		
		return bookmarkInfoDto;
	}

	@Override
	public void deleteBookmark(String memberId, List<Long> facilityId) {
		// TODO Auto-generated method stub
		bookmarkDao.deleteBookmark(memberId, facilityId);
	}
	
}
