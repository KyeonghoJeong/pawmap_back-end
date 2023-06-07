package com.pawmap.board.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.board.entity.ArticleEntity;

public interface ArticleDao {

	void postArticle(ArticleEntity articleEntity);

	Page<ArticleEntity> getArticles(Pageable pageable);

	ArticleEntity getArticles(Long articleId);

	String deleteArticle(Long articleId, String memberId);

}
