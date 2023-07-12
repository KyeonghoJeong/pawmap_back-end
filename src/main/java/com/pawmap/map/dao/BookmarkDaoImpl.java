package com.pawmap.map.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.pawmap.map.entity.BookmarkEntity;
import com.pawmap.map.entity.FacilityEntity;
import com.pawmap.map.repository.BookmarkRepository;
import com.pawmap.map.repository.FacilityRepository;

@Repository
public class BookmarkDaoImpl implements BookmarkDao {
	
	@Autowired
	private BookmarkRepository bookmarkRepository;
	
	@Autowired
	private FacilityRepository facilityRepository;

	@Override
	public boolean postBookmark(BookmarkEntity bookmarkEntity) {
		// TODO Auto-generated method stub
		// 회원 id와 시설 id를 조건으로 북마크 테이블에 등록되어 있는 북마크를 카운트
		Long count = bookmarkRepository.countByMemberIdAndFacilityId(bookmarkEntity.getMemberId(), bookmarkEntity.getFacilityId());

		// 카운트가 0개면 등록되어 있는 북마크가 없으므로 등록 가능
		if(count == 0) {
			bookmarkRepository.save(bookmarkEntity);
			
			return true;
		}else {
			// 카운트가 0개가 아니면 이미 등록되어 있는 북마크
			return false;
		}
	}

	@Override
	public Page<FacilityEntity> getBookmarks(String memberId, Pageable pageable) {
		// TODO Auto-generated method stub
		
		// 서브 쿼리로 북마크 테이블에서 memberId로 조회하여 facilityId를 가져오고 시설 테이블에서 가져온 id로 조회
		Page<FacilityEntity> facilityEntities = facilityRepository.getBookmarks(memberId, pageable);
		
		return facilityEntities;
	}

	@Override
	public void deleteBookmarks(List<Long> facilityIds, String memberId) {
		// TODO Auto-generated method stub
		
		// facilityIds 사이즈 만큼 반복해서 memberId와 요청한 시설 id로 조회하여 북마크 하나씩 삭제
		for(int i=0; i<facilityIds.size(); i++) {
			bookmarkRepository.deleteByFacilityIdAndMemberId(facilityIds.get(i), memberId);
		}
	}

}
