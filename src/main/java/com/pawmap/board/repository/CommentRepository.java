package com.pawmap.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pawmap.board.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

	// @Modifying�� nativeQuery���� ����, ���� �� �ݵ�� �ʿ�
	// cmtid(pk)�� ������ row ��� ���� writing���� ����
	@Modifying
	@Query(value="UPDATE cmt SET writing = :writing WHERE cmtid = :cmtid", nativeQuery=true)
	void putComment(@Param("cmtid") Long cmtId, @Param("writing") String writing);
	
	// �ش��ϴ� �Խñ� id�� ��� ���̺��� ��ȸ�Ͽ� �ش��ϴ� ��� �� count ����
	Long countByArticleId(Long articleId);

	// ��� ���̺��� �Խñ� id�� ��ȸ�ϰ� ��� �ۼ��� ���� ����
	Page<CommentEntity> findByArticleIdOrderByPostDateAsc(Long articleId, Pageable pageable);

}
