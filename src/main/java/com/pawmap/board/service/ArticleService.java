package com.pawmap.board.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.board.dto.ArticleDto;

public interface ArticleService {

	void postArticle(String memberId, Map<String, String> article);

	Page<ArticleDto> getArticles(Pageable pageable);

	ArticleDto getArticles(Long articleId);

	String deleteArticle(Long articleId, String memberId);

}
