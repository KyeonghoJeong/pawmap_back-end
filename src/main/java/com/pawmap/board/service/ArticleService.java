package com.pawmap.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.board.dto.ArticleDto;

public interface ArticleService {

	ArticleDto getArticle(Long articleId);
	
	void postArticle(Map<String, String> article, String memberId);
	
	void putArticle(Map<String, String> article, String memberId);
	
	void deleteArticle(Long articleId, String memberId);
	
	Map<String, Object> identifyMember(Long articleId, String memberId);

	Page<ArticleDto> getArticles(String title, String writing, String nickname, String memberId, Pageable pageable);

	void deleteArticles(List<Long> articleIds);

}
