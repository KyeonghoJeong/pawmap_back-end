package com.pawmap.bookmark.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pawmap.bookmark.entity.BookmarkEntity;
import com.pawmap.bookmark.repository.BookmarkInfoRepository;
import com.pawmap.bookmark.repository.BookmarkRepository;
import com.pawmap.facility.entity.FacilityEntity;

@Repository
public class BookmarkDaoImpl implements BookmarkDao {
	
	@Autowired
	private BookmarkRepository bookmarkRepository;

	@Autowired
	private BookmarkInfoRepository bookmarkInfoRepository;
	
	@Override
	public String addBookmark(BookmarkEntity bookmarkEntity) {
		// TODO Auto-generated method stub
		Long count = bookmarkRepository.countByMemberIdAndFacilityId(bookmarkEntity.getMemberId(), bookmarkEntity.getFacilityId());

		if(count != 0) {
			return "Duplicated";
		}else {
			bookmarkRepository.save(bookmarkEntity);
			
			return "Success";
		}
	}

	@Override
	public Page<FacilityEntity> getBookmarkInfo(String memberId, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = bookmarkInfoRepository.getInfo(memberId, pageable);
		
		return facilityEntities;
	}

	@Override
	@Transactional
	public void deleteBookmark(String memberId, List<Long> facilityId) {
		// TODO Auto-generated method stub
		for(int i=0; i<facilityId.size(); i++) {
			bookmarkRepository.deleteByMemberIdAndFacilityId(memberId, facilityId.get(i));
		}
	}

	@Override
	public void deleteBookmarks(String memberId) {
		// TODO Auto-generated method stub
		bookmarkRepository.deleteAllByMemberId(memberId);
	}

}
