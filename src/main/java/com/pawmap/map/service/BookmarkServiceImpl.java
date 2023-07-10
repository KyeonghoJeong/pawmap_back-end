package com.pawmap.map.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawmap.map.dao.BookmarkDao;
import com.pawmap.map.dto.BookmarkDto;
import com.pawmap.map.dto.BookmarkFacilityDto;
import com.pawmap.map.entity.BookmarkEntity;
import com.pawmap.map.entity.FacilityEntity;

@Service
@Transactional // 트랜잭션 설정
public class BookmarkServiceImpl implements BookmarkService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BookmarkDao bookmarkDao;

	// 북마크 추가 서비스 메소드
	@Override
	public String postBookmark(BookmarkDto bookmarkDto) {
		// TODO Auto-generated method stub
	
		bookmarkDto.setMemberId(SecurityContextHolder.getContext().getAuthentication().getName()); // 토큰에서 회원 id 가져와서 저장
		
		// BookmarkEntity 객체 생성 <= BookmarkDTO 매핑
		BookmarkEntity bookmarkEntity = modelMapper.map(bookmarkDto, BookmarkEntity.class); // ModelMapper 클래스로 dto => entity로 변환
		
		String result = bookmarkDao.postBookmark(bookmarkEntity); // 북마크 추가 dao 호출
		
		return result;
	}
	
	@Override
	public Page<BookmarkFacilityDto> getBookmarks(Pageable pageable) {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder에서 회원 아이디 가져오기 
		
		// dao를 통해 entity 받아오기
		Page<FacilityEntity> facilityEntities = bookmarkDao.getBookmarks(memberId, pageable);
		
		// 생성자를 이용해 서로 가지고 있는 변수만 매핑
		Page<BookmarkFacilityDto> bookmarkFacilityDto = facilityEntities.map(BookmarkFacilityDto::new);
		
		return bookmarkFacilityDto;
	}

	// 북마크 삭제 서비스 메소드
	@Override
	public void deleteBookmarks(List<Long> facilityIds) {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder에서 회원 아이디 가져오기 
		
		bookmarkDao.deleteBookmarks(facilityIds, memberId);
	}

}
