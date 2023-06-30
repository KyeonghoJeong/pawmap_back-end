package com.pawmap.bookmark.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

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
	
	// 북마크 추가 dao
	@Override
	public String addBookmark(BookmarkEntity bookmarkEntity) {
		// TODO Auto-generated method stub
		// 회원 id와 시설 id를 조건으로 북마크 테이블에 등록되어 있는 북마크를 카운트
		Long count = bookmarkRepository.countByMemberIdAndFacilityId(bookmarkEntity.getMemberId(), bookmarkEntity.getFacilityId());

		// 카운트가 0개면 등록되어 있는 북마크가 없으므로 등록 가능
		if(count == 0) {
			bookmarkRepository.save(bookmarkEntity);
			
			return "success";
		}else {
			// 카운트가 0개가 아니면 이미 등록되어 있는 북마크
			return "addedBookmark";
		}
	}

	@Override
	public Page<FacilityEntity> getBookmarkInfo(String memberId, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<FacilityEntity> facilityEntities = bookmarkInfoRepository.getInfo(memberId, pageable);
		
		return facilityEntities;
	}

	@Override
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
