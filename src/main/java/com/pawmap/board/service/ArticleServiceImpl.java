package com.pawmap.board.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
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
@Transactional // Ʈ����� ����
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ArticleDao articleDao;

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public ArticleDto getArticle(Long articleId) {
		// TODO Auto-generated method stub
		ArticleEntity articleEntity = articleDao.getArticle(articleId); // �Խñ� id�� ��ȸ�Ͽ� dao���� entity �ޱ�
		
		// dto �⺻������ �ʿ�
		ArticleDto articleDto = modelMapper.map(articleEntity, ArticleDto.class); // entity���� dto�� ����
		
		return articleDto;
	}
	
	@Override
	public void postArticle(Map<String, String> article, String memberId) {
		// TODO Auto-generated method stub
		MemberEntity memberEntity = memberDao.getMember(memberId); // ȸ�� ���̵�� ���� ��������
		
		String nickname = memberEntity.getNickname(); // �г��� ��������
		Date currentTime = Calendar.getInstance().getTime(); // ���� �ð�

		// �Խñ� entity ����
		// null�� �Խñ� id�� �������� �ڵ� ����
		ArticleEntity articleEntity = new ArticleEntity(
				null,
				memberId, // ȸ�� ���̵�
				nickname, // �г���
				article.get("title"), // ����
				article.get("writing"), // ����
				currentTime // ���� �ð�
		);
		
		articleDao.postArticle(articleEntity); // dao ȣ��
	}
	
	@Override
	public void putArticle(Map<String, String> article, String memberId) {
		// TODO Auto-generated method stub
		Long articleId = Long.parseLong(article.get("articleId")); // �Խñ� id ����ȯ
		String title = article.get("title"); // ����
		String writing = article.get("writing"); // ����
		
		articleDao.putArticle(articleId, title, writing); // dao ȣ��
	}
	
	@Override
	public void deleteArticle(Long articleId, String memberId) {
		// TODO Auto-generated method stub
		articleDao.deleteArticle(articleId, memberId);
	}
	
	@Override
	public Map<String, Object> identifyMember(Long articleId, String memberId) {
		// TODO Auto-generated method stub
		Long count = articleDao.identifyMember(articleId, memberId); // �Խñ� id, ȸ�� id�� ȣ��
		
		// ī��Ʈ�� 0�̸� �Խñ� �ۼ��ڿ� �α��� ���� ȸ�� ��ġ���� ���� (false)
		// ī��Ʈ�� 0�� �ƴϸ� ��ġ
		boolean isItsMember = false;
		if(count != 0) {
			isItsMember = true;
		}
		
		// ȸ�� ���̵�� �ۼ��� ���� ����
		Map<String, Object> result = new HashMap<>();
		result.put("memberId", memberId);
		result.put("isItsMember", isItsMember);
		
		return result;
	}

	@Override
	public Page<ArticleDto> getArticles(String title, String writing, String nickname, String memberId, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<ArticleEntity> articleEntities = articleDao.getArticles(title, writing, nickname, memberId, pageable); // dao ȣ��
		
		Page<ArticleDto> articleDtos = articleEntities.map(ArticleDto::new); // Page�� �״�� �����ڸ� �̿��ؼ� ����
		
		return articleDtos;
	}

	@Override
	public void deleteArticles(List<Long> articleIds) {
		// TODO Auto-generated method stub
		articleDao.deleteArticles(articleIds);
	}

}
