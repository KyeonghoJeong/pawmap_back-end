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
	
	// 게시판 기능
	
	// @Modifying은 nativeQuery에서 수정, 삭제 시 반드시 필요한 annotation
	// where 절로 게시글 id(pk)와 일치하는 게시글의 제목과 내용 수정
	@Modifying
	@Query(value="UPDATE article SET title = :title, writing = :writing WHERE articleId = :articleId", nativeQuery=true)
	void putArticle(@Param("articleId") Long articleId, @Param("title") String title, @Param("writing") String writing);
	
	// 게시글 id, 회원 id로 조회하여 게시글 삭제
	void deleteByArticleIdAndMemberId(Long articleId, String memberId);
	
	// 게시글 중 입력 받은 회원 id와 게시글 id 값을 가지고 있는 row 수 조회
	@Query(value="SELECT COUNT(*) FROM article WHERE articleId = :articleid AND memberId = :memberid", nativeQuery=true)
	Long identifyMember(@Param("articleid") Long articleId, @Param("memberid") String memberId);
	
	// 일반적인 게시판 조회 기능
	
	// 게시판 전체 게시글 조회
	// 최신 게시글 우선 조회 => 게시글 작성 날짜 역순으로 정렬 
	Page<ArticleEntity> findByOrderByPostDateDesc(Pageable pageable);
	
	// 제목+내용 검색 시 해당하는 게시글 조회
	// LIKE를 사용하여 검색어를 제목 또는 내용에 포함하는 게시글 모두 조회
	@Query(value="SELECT * FROM article WHERE title LIKE %:title% OR writing LIKE %:writing% ORDER BY postdate desc", nativeQuery=true)
	Page<ArticleEntity> getArticlesByTitleOrWritingOrderByPostDateDesc(@Param("title") String title, @Param("writing") String writing, Pageable pageable);
	
	// 제목 또는 내용 검색 시 해당하는 게시글 조회
	// LIKE를 사용하여 검색어를 포함하는 게시글 모두 조회
	// 제목 또는 내용 이므로 title 또는 writing 파라미터 중 한 쪽은 항상 값이 '' => 조회 시 포함되지 않음
	@Query(value="SELECT * FROM article WHERE title LIKE %:title% AND writing LIKE %:writing% ORDER BY postdate desc", nativeQuery=true)
	Page<ArticleEntity> getArticlesByTitleAndWritingOrderByPostDateDesc(@Param("title") String title, @Param("writing") String writing, Pageable pageable);
	
	// 닉네임 검색 시 해당하는 게시글 조회
	// 최신 게시글 우선 조회 => 게시글 작성 날짜 역순으로 정렬 
	Page<ArticleEntity> findByNicknameOrderByPostDateDesc(String nickname, Pageable pageable);
	
	// 특정 회원 게시글 조회 기능

	// 특정 회원 전체 게시글 조회 => memberId로 게시글 테이블 조회
	// 최신 게시글 우선 조회 => 게시글 작성 날짜 역순으로 정렬 
	Page<ArticleEntity> findByMemberIdOrderByPostDateDesc(String memberId, Pageable pageable);
	
	// 특정 회원 제목+내용 검색 시 해당하는 게시글 조회 => where절에 memberId
	// LIKE를 사용하여 검색어를 제목 또는 내용에 포함하는 게시글 모두 조회
	// 최신 게시글 우선 조회 => 게시글 작성 날짜 역순으로 정렬 
	@Query(value="SELECT * FROM article WHERE (title LIKE %:title% OR writing LIKE %:writing%) AND memberId = :memberid ORDER BY postdate desc", nativeQuery=true)
	Page<ArticleEntity> getArticlesByTitleOrWritingWithMemberIdOrderByPostDateDesc(@Param("title") String title, @Param("writing") String writing, @Param("memberid") String memberId, Pageable pageable);

	// 특정 회원 제목 또는 내용 검색 시 해당하는 게시글 조회 => where절에 memberId
	// LIKE를 사용하여 검색어를 포함하는 게시글 모두 조회
	// 제목 또는 내용 이므로 title 또는 writing 파라미터 중 한 쪽은 항상 값이 '' => 조회 시 포함되지 않음
	@Query(value="SELECT * FROM article WHERE (title LIKE %:title% AND writing LIKE %:writing%) AND memberId = :memberid ORDER BY postdate desc", nativeQuery=true)
	Page<ArticleEntity> getArticlesByTitleAndWritingWithMemberIdOrderByPostDateDesc(@Param("title") String title, @Param("writing") String writing, @Param("memberid") String memberId, Pageable pageable);
	
	// 관리자 권한
	
	// 게시글 삭제 
	void deleteByArticleId(Long articleId);

}
