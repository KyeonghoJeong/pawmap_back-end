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
	public Page<ArticleEntity> getArticles(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<ArticleEntity> articeEntities = articeRepository.findByOrderByPostDateDesc(pageable);
		
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
	public String deleteArticle(Long articleId, String memberId) {
		// TODO Auto-generated method stub
		Long count = articeRepository.getCount(articleId, memberId);
		
		if(count == 0) {
			return "Failed";
		}else {
			articeRepository.deleteByArticleIdAndMemberId(articleId, memberId);
			
			return "Success";
		}
	}

}