package com.pawmap.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pawmap.board.entity.ArticleEntity;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long>{
	
	// 일반적인 게시판 조회 기능
	
	// 게시판 전체 게시글 조회
	Page<ArticleEntity> findByOrderByPostDateDesc(Pageable pageable);
	
	// 제목+내용 검색 시 해당하는 게시글 조회
	@Query(value="SELECT * FROM article WHERE title LIKE %:title% OR writing LIKE %:writing% ORDER BY postdate desc", nativeQuery=true)
	Page<ArticleEntity> getArticlesByTitleOrWritingOrderByPostDateDesc(@Param("title") String title, @Param("writing") String writing, Pageable pageable);
	
	// 제목 또는 내용 검색 시 해당하는 게시글 조회
	@Query(value="SELECT * FROM article WHERE title LIKE %:title% AND writing LIKE %:writing% ORDER BY postdate desc", nativeQuery=true)
	Page<ArticleEntity> getArticlesByTitleAndWritingOrderByPostDateDesc(@Param("title") String title, @Param("writing") String writing, Pageable pageable);
	
	// 닉네임 검색 시 해당하는 게시글 조회
	Page<ArticleEntity> findByNicknameOrderByPostDateDesc(String nickname, Pageable pageable);
	
	// 특정 회원 게시글 조회 기능

	// 특정 회원 전체 게시글 조회
	Page<ArticleEntity> findByMemberIdOrderByPostDateDesc(String memberId, Pageable pageable);
	
	// 특정 회원 제목+내용 검색 시 해당하는 게시글 조회
	@Query(value="SELECT * FROM article WHERE (title LIKE %:title% OR writing LIKE %:writing%) AND memberId = :memberid ORDER BY postdate desc", nativeQuery=true)
	Page<ArticleEntity> getArticlesByTitleOrWritingWithMemberIdOrderByPostDateDesc(@Param("title") String title, @Param("writing") String writing, @Param("memberid") String memberId, Pageable pageable);

	// 특정 회원 제목 또는 내용 검색 시 해당하는 게시글 조회
	@Query(value="SELECT * FROM article WHERE (title LIKE %:title% AND writing LIKE %:writing%) AND memberId = :memberid ORDER BY postdate desc", nativeQuery=true)
	Page<ArticleEntity> getArticlesByTitleAndWritingWithMemberIdOrderByPostDateDesc(@Param("title") String title, @Param("writing") String writing, @Param("memberid") String memberId, Pageable pageable);
	
	//
	
	@Query(value="SELECT COUNT(*) FROM article WHERE articleId = :articleid AND memberId = :memberid", nativeQuery=true)
	Long getCount(@Param("articleid") Long articleId, @Param("memberid") String memberId);

	void deleteByArticleIdAndMemberId(Long articleId, String memberId);

	@Modifying
	@Query(value="UPDATE article SET title = :title, writing = :writing WHERE articleId = :articleId", nativeQuery=true)
	void putArticle(@Param("articleId") Long articleId, @Param("title") String title, @Param("writing") String writing);

	void deleteByArticleId(Long long1);

}
