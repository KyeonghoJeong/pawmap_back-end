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
	public String postBookmark(BookmarkEntity bookmarkEntity) {
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
	public Page<FacilityEntity> getBookmarks(String memberId, Pageable pageable) {
		// TODO Auto-generated method stub
		
		// ���� ������ �ϸ�ũ ���̺��� memberId�� ��ȸ�Ͽ� facilityId�� �������� �ü� ���̺��� ������ id�� ��ȸ
		Page<FacilityEntity> facilityEntities = facilityRepository.getBookmarks(memberId, pageable);
		
		return facilityEntities;
	}

	@Override
	public void deleteBookmarks(String memberId, List<Long> facilityIds) {
		// TODO Auto-generated method stub
		
		// facilityIds ������ ��ŭ �ݺ��ؼ� memberId�� ��û�� �ü� id�� ��ȸ�Ͽ� �ϸ�ũ �ϳ��� ����
		for(int i=0; i<facilityIds.size(); i++) {
			bookmarkRepository.deleteByMemberIdAndFacilityId(memberId, facilityIds.get(i));
		}
	}

}
