package com.pawmap.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pawmap.map.entity.BookmarkEntity;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long>{
	
	// ȸ�� ���̵� + �ü� id�� ��ȸ�Ͽ� ��ġ�ϴ� row �� ����
	Long countByMemberIdAndFacilityId(String memberId, Long facilityId);
	
	// �ü� id + ȸ�� ���̵�� ��ġ�ϴ� �ϸ�ũ row ���� 
	void deleteByFacilityIdAndMemberId(Long facilityId, String memberId);

	// ȸ�� ���̵�� ��ȸ�Ͽ� �ش��ϴ� ��� �ϸ�ũ ����
	void deleteAllByMemberId(String memberId);

}
