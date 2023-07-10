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
	public ArticleEntity getArticle(Long articleId) {
		// TODO Auto-generated method stub
		Optional<ArticleEntity> optionalArticleEntity = articleRepository.findById(articleId); // �Խñ� id�� �Խñ� ��ȸ
		
		ArticleEntity articleEntity = null;
		
		if(optionalArticleEntity != null) {
			articleEntity = optionalArticleEntity.get(); // null�� �ƴϸ� �� ��������
		}
		
		return articleEntity;
	}
	
	@Override
	public void postArticle(ArticleEntity articleEntity) {
		// TODO Auto-generated method stub
		articleRepository.save(articleEntity); // repository save �޼ҵ�� entity ����
	}
	
	@Override
	public void putArticle(Long articleId, String title, String writing) {
		// TODO Auto-generated method stub		
		articleRepository.putArticle(articleId, title, writing); // articleRepository �޼ҵ� ȣ��
	}

	@Override
	public void deleteArticle(Long articleId, String memberId) {
		// TODO Auto-generated method stub
		articleRepository.deleteByArticleIdAndMemberId(articleId, memberId); // �Խñ� id, ȸ�� id�� ��ȸ�Ͽ� ����
	}
	
	@Override
	public Long getMemberIdentification(Long articleId, String memberId) {
		// TODO Auto-generated method stub
		Long count = articleRepository.getMemberIdentification(articleId, memberId); // �Խñ� id, ȸ�� id�� ȣ��
		
		return count;
	}
	
	@Override
	public Page<ArticleEntity> getArticles(String title, String writing, String nickname, String memberId, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<ArticleEntity> articleEntities = null; // ��ƼƼ ��ü Page������ ����
		
		// �Ķ���� ������ ���� ����
		
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

		// Ư�� ȸ�� �Խñ� ��ȸ ��� => memberId�� ""�� �ƴ� ���
		
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
	public void deleteArticles(List<Long> articleIds) {
		// TODO Auto-generated method stub
		for(int i=0; i<articleIds.size(); i++) {
			// ���� ��û���� ���� �Խñ��� ����ŭ �ݺ�
			// �Խñ� id�� �Խñ� ���� ��û �ݺ�
			articleRepository.deleteByArticleId(articleIds.get(i));
		}
	}

}
