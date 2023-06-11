package com.pawmap.board.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.board.entity.ArticleEntity;

public interface ArticleDao {

	void postArticle(ArticleEntity articleEntity);

	Page<ArticleEntity> getArticles(String title, String writing, String nickname, String memberId, Pageable pageable);

	ArticleEntity getArticles(Long articleId);

	void deleteArticle(Long articleId, String memberId);

	void putArticle(Long articleId, String title, String writing);

	void deleteArticles(List<Long> articleIds);

}
