package com.pawmap.map.service;

import java.util.List;
import java.util.Map;

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
@Transactional // Ʈ����� ����
public class BookmarkServiceImpl implements BookmarkService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BookmarkDao bookmarkDao;

	// �ϸ�ũ �߰� ���� �޼ҵ�
	@Override
	public String postBookmark(Map<String, Long> facilityData) {
		// TODO Auto-generated method stub
		
		// �Է¹��� �����Ϳ��� �ü� id ��������
		Long facilityId = facilityData.get("facilityId");
		
		// BookmarkDto ��ü ����
		BookmarkDto bookmarkDto = new BookmarkDto();
		bookmarkDto.setMemberId(SecurityContextHolder.getContext().getAuthentication().getName()); // ��ū���� ȸ�� id ��������
		bookmarkDto.setFacilityId(facilityId); // ����Ʈ���忡�� ���� �ü� id
		
		// BookmarkEntity ��ü ���� <= BookmarkDTO ����
		BookmarkEntity bookmarkEntity = modelMapper.map(bookmarkDto, BookmarkEntity.class); // ModelMapper Ŭ������ dto => entity�� ��ȯ
		
		String result = bookmarkDao.postBookmark(bookmarkEntity); // �ϸ�ũ �߰� dao ȣ��
		
		return result;
	}
	
	@Override
	public Page<BookmarkFacilityDto> getBookmarks(String memberId, Pageable pageable) {
		// TODO Auto-generated method stub
		
		// dao�� ���� entity �޾ƿ���
		Page<FacilityEntity> facilityEntities = bookmarkDao.getBookmarks(memberId, pageable);
		
		// �����ڸ� �̿��� ���� ������ �ִ� ������ ����
		Page<BookmarkFacilityDto> bookmarkFacilityDto = facilityEntities.map(BookmarkFacilityDto::new);
		
		return bookmarkFacilityDto;
	}

	// �ϸ�ũ ���� ���� �޼ҵ�
	@Override
	public void deleteBookmarks(List<Long> facilityIds, String memberId) {
		// TODO Auto-generated method stub
		bookmarkDao.deleteBookmarks(facilityIds, memberId);
	}

}
