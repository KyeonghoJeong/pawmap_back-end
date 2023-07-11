package com.pawmap.board.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawmap.board.dao.ArticleDao;
import com.pawmap.board.dto.ArticleDto;
import com.pawmap.board.dto.MemberIdentificationDto;
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
	public void postArticle(ArticleDto articleDto) {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder���� ȸ�� ���̵� �������� 
		
		MemberEntity memberEntity = memberDao.getMember(memberId); // ȸ�� ���̵�� ���� ��������
		
		String nickname = memberEntity.getNickname(); // �г��� ��������
		Date currentTime = Calendar.getInstance().getTime(); // ���� �ð�

		// �Խñ� entity ����
		// null�� �Խñ� id�� �������� �ڵ� ����
		ArticleEntity articleEntity = new ArticleEntity(
				null,
				memberId, // ȸ�� ���̵�
				nickname, // �г���
				articleDto.getTitle(), // ����
				articleDto.getWriting(), // ����
				currentTime // ���� �ð�
		);
		
		articleDao.postArticle(articleEntity); // dao ȣ��
	}
	
	@Override
	public void putArticle(ArticleDto articleDto) {
		// TODO Auto-generated method stub
		Long articleId = articleDto.getArticleId(); // �Խñ� id ����ȯ
		String title = articleDto.getTitle(); // ����
		String writing = articleDto.getWriting(); // ����
		
		articleDao.putArticle(articleId, title, writing); // dao ȣ��
	}
	
	@Override
	public void deleteArticle(Long articleId) {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder���� ȸ�� ���̵� ��������
		
		articleDao.deleteArticle(articleId, memberId);
	}
	
	@Override
	public MemberIdentificationDto getMemberIdentification(Long articleId) {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder���� ȸ�� ���̵� ��������
		
		Long count = articleDao.getMemberIdentification(articleId, memberId); // �Խñ� id, ȸ�� id�� ȣ��
		
		// ī��Ʈ�� 0�̸� �Խñ� �ۼ��ڿ� �α��� ���� ȸ�� ��ġ���� ���� (false)
		// ī��Ʈ�� 0�� �ƴϸ� ��ġ
		boolean itsMember = false;
		if(count != 0) {
			itsMember = true;
		}
		
		// ȸ�� ���̵�� �ۼ��� ���� dto ��ü ����
		MemberIdentificationDto memberIdentificationDto = new MemberIdentificationDto();
		memberIdentificationDto.setMemberId(memberId);
		memberIdentificationDto.setItsMember(itsMember);
		
		return memberIdentificationDto;
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
