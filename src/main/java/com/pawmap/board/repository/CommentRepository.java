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

	// @Modifying은 nativeQuery에서 수정, 삭제 시 반드시 필요
	// cmtid(pk)가 동일한 row 댓글 내용 writing으로 수정
	@Modifying
	@Query(value="UPDATE cmt SET writing = :writing WHERE cmtid = :cmtid", nativeQuery=true)
	void putComment(@Param("cmtid") Long cmtId, @Param("writing") String writing);
	
	// 해당하는 게시글 id로 댓글 테이블을 조회하여 해당하는 댓글 수 count 리턴
	Long countByArticleId(Long articleId);

	// 댓글 테이블에서 게시글 id로 조회하고 댓글 작성일 순차 정렬
	Page<CommentEntity> findByArticleIdOrderByPostDateAsc(Long articleId, Pageable pageable);

}
