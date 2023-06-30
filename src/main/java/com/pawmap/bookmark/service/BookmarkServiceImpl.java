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

	// �ϸ�ũ �߰� ���� �޼ҵ�
	@Override
	public String addBookmark(Long facilityId) {
		// TODO Auto-generated method stub
		BookmarkDto bookmarkDto = new BookmarkDto();
		bookmarkDto.setMemberId(SecurityContextHolder.getContext().getAuthentication().getName()); // ��ū���� ȸ�� id ��������
		bookmarkDto.setFacilityId(facilityId); // ����Ʈ���忡�� ���� �ü� id
		
		BookmarkEntity bookmarkEntity = modelMapper.map(bookmarkDto, BookmarkEntity.class); // ModelMapper Ŭ������ dto => entity�� ��ȯ
		
		String result = bookmarkDao.addBookmark(bookmarkEntity); // �ϸ�ũ �߰� dao ȣ��
		
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
