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
@Transactional // 트랜잭션 설정
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
		ArticleEntity articleEntity = articleDao.getArticle(articleId); // 게시글 id로 조회하여 dao에서 entity 받기
		
		// dto 기본생성자 필요
		ArticleDto articleDto = modelMapper.map(articleEntity, ArticleDto.class); // entity에서 dto로 매핑
		
		return articleDto;
	}
	
	@Override
	public void postArticle(ArticleDto articleDto) {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder에서 회원 아이디 가져오기 
		
		MemberEntity memberEntity = memberDao.getMember(memberId); // 회원 아이디로 정보 가져오기
		
		String nickname = memberEntity.getNickname(); // 닉네임 가져오기
		Date currentTime = Calendar.getInstance().getTime(); // 현재 시간

		// 게시글 entity 생성
		// null은 게시글 id로 시퀀스로 자동 생성
		ArticleEntity articleEntity = new ArticleEntity(
				null,
				memberId, // 회원 아이디
				nickname, // 닉네임
				articleDto.getTitle(), // 제목
				articleDto.getWriting(), // 내용
				currentTime // 현재 시간
		);
		
		articleDao.postArticle(articleEntity); // dao 호출
	}
	
	@Override
	public void putArticle(ArticleDto articleDto) {
		// TODO Auto-generated method stub
		Long articleId = articleDto.getArticleId(); // 게시글 id 형변환
		String title = articleDto.getTitle(); // 제목
		String writing = articleDto.getWriting(); // 내용
		
		articleDao.putArticle(articleId, title, writing); // dao 호출
	}
	
	@Override
	public void deleteArticle(Long articleId) {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder에서 회원 아이디 가져오기
		
		articleDao.deleteArticle(articleId, memberId);
	}
	
	@Override
	public MemberIdentificationDto getMemberIdentification(Long articleId) {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder에서 회원 아이디 가져오기
		
		Long count = articleDao.getMemberIdentification(articleId, memberId); // 게시글 id, 회원 id로 호출
		
		// 카운트가 0이면 게시글 작성자와 로그인 중인 회원 일치하지 않음 (false)
		// 카운트가 0이 아니면 일치
		boolean itsMember = false;
		if(count != 0) {
			itsMember = true;
		}
		
		// 회원 아이디와 작성자 여부 dto 객체 생성
		MemberIdentificationDto memberIdentificationDto = new MemberIdentificationDto();
		memberIdentificationDto.setMemberId(memberId);
		memberIdentificationDto.setItsMember(itsMember);
		
		return memberIdentificationDto;
	}

	@Override
	public Page<ArticleDto> getArticles(String title, String writing, String nickname, String memberId, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<ArticleEntity> articleEntities = articleDao.getArticles(title, writing, nickname, memberId, pageable); // dao 호출
		
		Page<ArticleDto> articleDtos = articleEntities.map(ArticleDto::new); // Page형 그대로 생성자를 이용해서 매핑
		
		return articleDtos;
	}

	@Override
	public void deleteArticles(List<Long> articleIds) {
		// TODO Auto-generated method stub
		articleDao.deleteArticles(articleIds);
	}

}
