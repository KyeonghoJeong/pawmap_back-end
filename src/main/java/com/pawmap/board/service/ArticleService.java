package com.pawmap.board.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.board.dto.ArticleDto;

public interface ArticleService {

	void postArticle(String memberId, Map<String, String> article);

	Page<ArticleDto> getArticles(String title, String writing, String nickname, String memberId, Pageable pageable);

	ArticleDto getArticles(Long articleId);

	void deleteArticle(Long articleId, String memberId);

	void putArticle(String memberId, Map<String, String> article);

}
