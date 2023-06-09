package com.pawmap.board.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.pawmap.board.entity.ArticleEntity;
import com.pawmap.board.repository.ArticleRepository;

@Repository
public class ArticleDaoImpl implements ArticleDao {
	
	@Autowired
	private ArticleRepository articeRepository;

	@Override
	public void postArticle(ArticleEntity articleEntity) {
		// TODO Auto-generated method stub
		articeRepository.save(articleEntity);
	}

	@Override
	public Page<ArticleEntity> getArticles(String title, String writing, String nickname, String memberId, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<ArticleEntity> articeEntities = null;
		
		if(title == "" && writing == "" && nickname == "" && memberId == "") {
			articeEntities = articeRepository.findByOrderByPostDateDesc(pageable);
		}else if(title != "" && writing != "" && nickname == "" && memberId == "") {
			articeEntities = articeRepository.findByTitleLikeOrWritingLikeOrderByPostDateDesc(title, writing, pageable);
		}else if(title != "" && writing == "" && nickname == "" && memberId == "") {
			articeEntities = articeRepository.findByTitleLikeOrderByPostDateDesc(title, pageable);
		}else if(title == "" && writing != "" && nickname == "" && memberId == "") {
			articeEntities = articeRepository.findByWritingLikeOrderByPostDateDesc(writing, pageable);
		}else if(title == "" && writing == "" && nickname != "" && memberId == "") {
			articeEntities = articeRepository.findByNicknameOrderByPostDateDesc(nickname, pageable);
		}else if(title == "" && writing == "" && nickname == "" && memberId != "") {
			articeEntities = articeRepository.findByMemberIdOrderByPostDateDesc(memberId, pageable);
		}else if(title != "" && writing != "" && nickname == "" && memberId != "") {
			articeEntities = articeRepository.getArticles(title, writing, memberId, pageable);
		}else if(title != "" && writing == "" && nickname == "" && memberId != "") {
			articeEntities = articeRepository.getArticlesByTitle(title, memberId, pageable);
		}else if(title == "" && writing != "" && nickname == "" && memberId != "") {
			articeEntities = articeRepository.getArticlesByWriting(writing, memberId, pageable);
		}
		
		return articeEntities;
	}

	@Override
	public ArticleEntity getArticles(Long articleId) {
		// TODO Auto-generated method stub
		Optional<ArticleEntity> optionalArticleEntity = articeRepository.findById(articleId);
		
		ArticleEntity articleEntity;
		if(optionalArticleEntity != null) {
			articleEntity = optionalArticleEntity.get();
			
			return articleEntity;
		}else {
			return null;
		}
	}

	@Override
	public void deleteArticle(Long articleId, String memberId) {
		// TODO Auto-generated method stub
		articeRepository.deleteByArticleIdAndMemberId(articleId, memberId);
	}

	@Override
	public void putArticle(Long articleId, String title, String writing) {
		// TODO Auto-generated method stub
		System.out.println(articleId);
		System.out.println(title);
		System.out.println(writing);
		
		articeRepository.putArticle(articleId, title, writing);
	}

}
