package com.pawmap.board.dao;

import java.util.List;
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
	private ArticleRepository articleRepository;
	
	@Override
	public Page<ArticleEntity> getArticles(String title, String writing, String nickname, String memberId, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<ArticleEntity> articleEntities = null;
		
		// �Ϲ����� �Խ��� ��ȸ ���
		
		// �Խ��� ��ü �Խñ� ��ȸ
		if(title == "" && writing == "" && nickname == "" && memberId == "") {
			articleEntities = articleRepository.findByOrderByPostDateDesc(pageable);
		}
		
		// ����+���� �˻� �� �ش��ϴ� �Խñ� ��ȸ
		if(title != "" && writing != "" && nickname == "" && memberId == "") {
			articleEntities = articleRepository.getArticlesByTitleOrWritingOrderByPostDateDesc(title, writing, pageable);
		}

		// ���� �Ǵ� ���� �˻� �� �ش��ϴ� �Խñ� ��ȸ
		if((title == "" && writing != "" && nickname == "" && memberId == "") || (title != "" && writing == "" && nickname == "" && memberId == "")) {
			articleEntities = articleRepository.getArticlesByTitleAndWritingOrderByPostDateDesc(title, writing, pageable);
		}
		
		// �г��� �˻� �� �ش��ϴ� �Խñ� ��ȸ
		if(title == "" && writing == "" && nickname != "" && memberId == "") {
			articleEntities = articleRepository.findByNicknameOrderByPostDateDesc(nickname, pageable);
		}

		// Ư�� ȸ�� �Խñ� ��ȸ ���
		
		// Ư�� ȸ�� ��ü �Խñ� ��ȸ
		if(title == "" && writing == "" && nickname == "" && memberId != "") {
			articleEntities = articleRepository.findByMemberIdOrderByPostDateDesc(memberId, pageable);
		}

		// Ư�� ȸ�� ����+���� �˻� �� �ش��ϴ� �Խñ� ��ȸ
		if(title != "" && writing != "" && nickname == "" && memberId != "") {
			articleEntities = articleRepository.getArticlesByTitleOrWritingWithMemberIdOrderByPostDateDesc(title, writing, memberId, pageable);
		}

		// Ư�� ȸ�� ���� �Ǵ� ���� �˻� �� �ش��ϴ� �Խñ� ��ȸ
		if((title != "" && writing == "" && nickname == "" && memberId != "") || (title == "" && writing != "" && nickname == "" && memberId != "")) {
			articleEntities = articleRepository.getArticlesByTitleAndWritingWithMemberIdOrderByPostDateDesc(title, writing, memberId, pageable);
		}
		
		return articleEntities;
	}

	@Override
	public void postArticle(ArticleEntity articleEntity) {
		// TODO Auto-generated method stub
		articleRepository.save(articleEntity);
	}

	@Override
	public ArticleEntity getArticles(Long articleId) {
		// TODO Auto-generated method stub
		Optional<ArticleEntity> optionalArticleEntity = articleRepository.findById(articleId);
		
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
		articleRepository.deleteByArticleIdAndMemberId(articleId, memberId);
	}

	@Override
	public void putArticle(Long articleId, String title, String writing) {
		// TODO Auto-generated method stub
		System.out.println(articleId);
		System.out.println(title);
		System.out.println(writing);
		
		articleRepository.putArticle(articleId, title, writing);
	}

	@Override
	public void deleteArticles(List<Long> articleIds) {
		// TODO Auto-generated method stub
		for(int i=0; i<articleIds.size(); i++) {
			articleRepository.deleteByArticleId(articleIds.get(i));
		}
	}

}
