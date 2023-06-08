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

	@Query(value="SELECT COUNT(*) FROM article WHERE articleId = :articleid AND memberId = :memberid", nativeQuery=true)
	Long getCount(@Param("articleid") Long articleId, @Param("memberid") String memberId);

	void deleteByArticleIdAndMemberId(Long articleId, String memberId);

	@Modifying
	@Query(value="UPDATE article SET title = :title, writing = :writing WHERE articleId = :articleId", nativeQuery=true)
	void putArticle(@Param("articleId") Long articleId, @Param("title") String title, @Param("writing") String writing);

}
