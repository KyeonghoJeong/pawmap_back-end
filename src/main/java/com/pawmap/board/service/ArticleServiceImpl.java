package com.pawmap.board.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawmap.board.dao.ArticleDao;
import com.pawmap.board.dto.ArticleDto;
import com.pawmap.board.entity.ArticleEntity;
import com.pawmap.member.dao.MemberDao;
import com.pawmap.member.entity.MemberEntity;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleDao articleDao;

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public ArticleDto getArticles(Long articleId) {
		// TODO Auto-generated method stub
		ArticleEntity articleEntity = articleDao.getArticles(articleId);
		
		ArticleDto articleDto = new ArticleDto(articleEntity);
		
		return articleDto;
	}
	
	@Override
	public void postArticle(String memberId, Map<String, String> article) {
		// TODO Auto-generated method stub
		MemberEntity memberEntity = memberDao.getMember(memberId);
		String nickname = memberEntity.getNickname();
		Date currentTime = Calendar.getInstance().getTime();

		ArticleEntity articleEntity = new ArticleEntity(
				null,
				memberId,
				nickname,
				article.get("title"),
				article.get("writing"),
				currentTime
		);
		
		articleDao.postArticle(articleEntity);
	}

	@Override
	public Page<ArticleDto> getArticles(String title, String writing, String nickname, String memberId, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<ArticleEntity> articleEntities = articleDao.getArticles(title, writing, nickname, memberId, pageable);
		
		Page<ArticleDto> articleDtos = articleEntities.map(ArticleDto::new);
		
		return articleDtos;
	}

	@Override
	public void deleteArticle(Long articleId, String memberId) {
		// TODO Auto-generated method stub
		articleDao.deleteArticle(articleId, memberId);
	}

	@Override
	public void putArticle(String memberId, Map<String, String> article) {
		// TODO Auto-generated method stub
		Long articleId = Long.parseLong(article.get("articleId"));
		String title = article.get("title");
		String writing = article.get("writing");
		
		articleDao.putArticle(articleId, title, writing);
	}

	@Override
	public void deleteArticles(List<Long> articleIds) {
		// TODO Auto-generated method stub
		articleDao.deleteArticles(articleIds);
	}

}
