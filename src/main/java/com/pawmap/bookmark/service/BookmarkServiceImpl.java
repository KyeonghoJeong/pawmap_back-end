package com.pawmap.bookmark.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawmap.bookmark.dao.BookmarkDao;
import com.pawmap.bookmark.dto.BookmarkDto;
import com.pawmap.bookmark.dto.BookmarkInfoDto;
import com.pawmap.bookmark.entity.BookmarkEntity;
import com.pawmap.facility.entity.FacilityEntity;

@Service
@Transactional
public class BookmarkServiceImpl implements BookmarkService {
	
	@Autowired
	private BookmarkDao bookmarkDao;
	
	@Autowired
	private ModelMapper modelMapper;

	// 북마크 추가 서비스 메소드
	@Override
	public String addBookmark(Long facilityId) {
		// TODO Auto-generated method stub
		BookmarkDto bookmarkDto = new BookmarkDto();
		bookmarkDto.setMemberId(SecurityContextHolder.getContext().getAuthentication().getName()); // 토큰에서 회원 id 가져오기
		bookmarkDto.setFacilityId(facilityId); // 프론트엔드에서 받은 시설 id
		
		BookmarkEntity bookmarkEntity = modelMapper.map(bookmarkDto, BookmarkEntity.class); // ModelMapper 클래스로 dto => entity로 변환
		
		String result = bookmarkDao.addBookmark(bookmarkEntity); // 북마크 추가 dao 호출
		
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

	@Override
	public void deleteBookmarks(String memberId) {
		// TODO Auto-generated method stub
		bookmarkDao.deleteBookmarks(memberId);
	}
	
}
