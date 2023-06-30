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
	
	// �ϸ�ũ �߰� dao
	@Override
	public String addBookmark(BookmarkEntity bookmarkEntity) {
		// TODO Auto-generated method stub
		// ȸ�� id�� �ü� id�� �������� �ϸ�ũ ���̺� ��ϵǾ� �ִ� �ϸ�ũ�� ī��Ʈ
		Long count = bookmarkRepository.countByMemberIdAndFacilityId(bookmarkEntity.getMemberId(), bookmarkEntity.getFacilityId());

		// ī��Ʈ�� 0���� ��ϵǾ� �ִ� �ϸ�ũ�� �����Ƿ� ��� ����
		if(count == 0) {
			bookmarkRepository.save(bookmarkEntity);
			
			return "success";
		}else {
			// ī��Ʈ�� 0���� �ƴϸ� �̹� ��ϵǾ� �ִ� �ϸ�ũ
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
