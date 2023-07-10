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
		Optional<ArticleEntity> optionalArticleEntity = articleRepository.findById(articleId); // 게시글 id로 게시글 조회
		
		ArticleEntity articleEntity = null;
		
		if(optionalArticleEntity != null) {
			articleEntity = optionalArticleEntity.get(); // null이 아니면 값 가져오기
		}
		
		return articleEntity;
	}
	
	@Override
	public void postArticle(ArticleEntity articleEntity) {
		// TODO Auto-generated method stub
		articleRepository.save(articleEntity); // repository save 메소드로 entity 저장
	}
	
	@Override
	public void putArticle(Long articleId, String title, String writing) {
		// TODO Auto-generated method stub		
		articleRepository.putArticle(articleId, title, writing); // articleRepository 메소드 호출
	}

	@Override
	public void deleteArticle(Long articleId, String memberId) {
		// TODO Auto-generated method stub
		articleRepository.deleteByArticleIdAndMemberId(articleId, memberId); // 게시글 id, 회원 id로 조회하여 삭제
	}
	
	@Override
	public Long getMemberIdentification(Long articleId, String memberId) {
		// TODO Auto-generated method stub
		Long count = articleRepository.getMemberIdentification(articleId, memberId); // 게시글 id, 회원 id로 호출
		
		return count;
	}
	
	@Override
	public Page<ArticleEntity> getArticles(String title, String writing, String nickname, String memberId, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<ArticleEntity> articleEntities = null; // 엔티티 객체 Page형으로 리턴
		
		// 파라미터 유무로 조건 적용
		
		// 일반적인 게시판 조회 기능
		
		// 게시판 전체 게시글 조회
		if(title == "" && writing == "" && nickname == "" && memberId == "") {
			articleEntities = articleRepository.findByOrderByPostDateDesc(pageable);
		}
		
		// 제목+내용 검색 시 해당하는 게시글 조회
		if(title != "" && writing != "" && nickname == "" && memberId == "") {
			articleEntities = articleRepository.getArticlesByTitleOrWritingOrderByPostDateDesc(title, writing, pageable);
		}

		// 제목 또는 내용 검색 시 해당하는 게시글 조회
		if((title == "" && writing != "" && nickname == "" && memberId == "") || (title != "" && writing == "" && nickname == "" && memberId == "")) {
			articleEntities = articleRepository.getArticlesByTitleAndWritingOrderByPostDateDesc(title, writing, pageable);
		}
		
		// 닉네임 검색 시 해당하는 게시글 조회
		if(title == "" && writing == "" && nickname != "" && memberId == "") {
			articleEntities = articleRepository.findByNicknameOrderByPostDateDesc(nickname, pageable);
		}

		// 특정 회원 게시글 조회 기능 => memberId가 ""가 아닌 경우
		
		// 특정 회원 전체 게시글 조회
		if(title == "" && writing == "" && nickname == "" && memberId != "") {
			articleEntities = articleRepository.findByMemberIdOrderByPostDateDesc(memberId, pageable);
		}

		// 특정 회원 제목+내용 검색 시 해당하는 게시글 조회
		if(title != "" && writing != "" && nickname == "" && memberId != "") {
			articleEntities = articleRepository.getArticlesByTitleOrWritingWithMemberIdOrderByPostDateDesc(title, writing, memberId, pageable);
		}

		// 특정 회원 제목 또는 내용 검색 시 해당하는 게시글 조회
		if((title != "" && writing == "" && nickname == "" && memberId != "") || (title == "" && writing != "" && nickname == "" && memberId != "")) {
			articleEntities = articleRepository.getArticlesByTitleAndWritingWithMemberIdOrderByPostDateDesc(title, writing, memberId, pageable);
		}
		
		return articleEntities;
	}

	@Override
	public void deleteArticles(List<Long> articleIds) {
		// TODO Auto-generated method stub
		for(int i=0; i<articleIds.size(); i++) {
			// 삭제 요청으로 들어온 게시글의 수만큼 반복
			// 게시글 id로 게시글 삭제 요청 반복
			articleRepository.deleteByArticleId(articleIds.get(i));
		}
	}

}
