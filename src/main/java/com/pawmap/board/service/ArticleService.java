package com.pawmap.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.board.dto.ArticleDto;
import com.pawmap.board.dto.MemberIdentificationDto;

public interface ArticleService {

	ArticleDto getArticle(Long articleId);
	
	void postArticle(ArticleDto articleDto);
	
	void putArticle(ArticleDto articleDto);
	
	void deleteArticle(Long articleId);
	
	MemberIdentificationDto getMemberIdentification(Long articleId);

	Page<ArticleDto> getArticles(String title, String writing, String nickname, String memberId, Pageable pageable);

	void deleteArticles(List<Long> articleIds);

}
