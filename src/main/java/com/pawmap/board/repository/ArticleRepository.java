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

	Page<ArticleEntity> findByOrderByPostDateDesc(Pageable pageable);
	
	@Query(value="SELECT * FROM article WHERE title LIKE %:title% OR writing LIKE %:writing% ORDER BY postdate desc", nativeQuery=true)
	Page<ArticleEntity> findByTitleLikeOrWritingLikeOrderByPostDateDesc(@Param("title") String title, @Param("writing") String writing, Pageable pageable);
	
	@Query(value="SELECT * FROM article WHERE title LIKE %:title% ORDER BY postdate desc", nativeQuery=true)
	Page<ArticleEntity> findByTitleLikeOrderByPostDateDesc(@Param("title") String title, Pageable pageable);

	@Query(value="SELECT * FROM article WHERE writing LIKE %:writing% ORDER BY postdate desc", nativeQuery=true)
	Page<ArticleEntity> findByWritingLikeOrderByPostDateDesc(@Param("writing") String writing, Pageable pageable);

	Page<ArticleEntity> findByNicknameOrderByPostDateDesc(String nickname, Pageable pageable);
	
	Page<ArticleEntity> findByMemberIdOrderByPostDateDesc(String memberId, Pageable pageable);
	
	@Query(value="SELECT * FROM article WHERE (title LIKE %:title% OR writing LIKE %:writing%) AND memberId = :memberid ORDER BY postdate desc", nativeQuery=true)
	Page<ArticleEntity> getArticles(@Param("title") String title, @Param("writing") String writing, @Param("memberid") String memberId, Pageable pageable);

	@Query(value="SELECT * FROM article WHERE title LIKE %:title% AND memberId = :memberid ORDER BY postdate desc", nativeQuery=true)
	Page<ArticleEntity> getArticlesByTitle(@Param("title") String title, @Param("memberid") String memberId, Pageable pageable);

	@Query(value="SELECT * FROM article WHERE writing LIKE %:writing% AND memberId = :memberid ORDER BY postdate desc", nativeQuery=true)
	Page<ArticleEntity> getArticlesByWriting(@Param("writing") String writing, @Param("memberid") String memberId, Pageable pageable);
	
	//
	
	@Query(value="SELECT COUNT(*) FROM article WHERE articleId = :articleid AND memberId = :memberid", nativeQuery=true)
	Long getCount(@Param("articleid") Long articleId, @Param("memberid") String memberId);

	void deleteByArticleIdAndMemberId(Long articleId, String memberId);

	@Modifying
	@Query(value="UPDATE article SET title = :title, writing = :writing WHERE articleId = :articleId", nativeQuery=true)
	void putArticle(@Param("articleId") Long articleId, @Param("title") String title, @Param("writing") String writing);

}
