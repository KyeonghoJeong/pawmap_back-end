package com.pawmap.board.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.board.entity.ArticleEntity;

public interface ArticleDao {

	ArticleEntity getArticle(Long articleId);
	
	void postArticle(ArticleEntity articleEntity);
	
	void putArticle(Long articleId, String title, String writing);
	
	void deleteArticle(Long articleId, String memberId);
	
	Long getMemberIdentification(Long articleId, String memberId);

	Page<ArticleEntity> getArticles(String title, String writing, String nickname, String memberId, Pageable pageable);

	void deleteArticles(List<Long> articleIds);

}
