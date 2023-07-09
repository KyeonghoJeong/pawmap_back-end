package com.pawmap.board.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawmap.board.dao.CommentDao;
import com.pawmap.board.dto.CommentDto;
import com.pawmap.board.entity.CommentEntity;
import com.pawmap.member.dao.MemberDao;
import com.pawmap.member.entity.MemberEntity;

@Service
@Transactional // 트랜잭션 지정
public class CommentServiceImpl implements CommentService {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private CommentDao commentDao;
	
	@Override
	public void postComment(Map<String, String> commentData, String memberId) {
		// TODO Auto-generated method stub
		MemberEntity memberEntity = memberDao.getMember(memberId); // 회원 아이디로 회원 엔티티 생성
		
		Long articleId = Long.parseLong(commentData.get("articleId")); // 게시글 id 형변환
		String nickname = memberEntity.getNickname(); // 회원 닉네임 가져오기
		String writing = commentData.get("writing"); // 댓글 내용
		Date currentTime = Calendar.getInstance().getTime(); // 현재 시간
		
		// 댓글 엔티티 생성
		// 파라미터 => 댓글 id(시퀀스), 게시글 id, 회원 id, 닉네임, 내용, 댓글 작성 시간  
		CommentEntity commentEntity = new CommentEntity(null, articleId, memberId, nickname, writing, currentTime);
	
		commentDao.postComment(commentEntity); // dao 호출
	}
	
	@Override
	public void putComment(Map<String, String> commentData) {
		// TODO Auto-generated method stub
		Long cmtId = Long.parseLong(commentData.get("cmtId")); // 댓글 id
		String writing = commentData.get("writing"); // 댓글 내용
		
		commentDao.putComment(cmtId, writing); // dao 호출
	}

	@Override
	public void deleteComment(Long cmtId) {
		// TODO Auto-generated method stub
		commentDao.deleteComment(cmtId); // dao 호출
	}

	@Override
	public Page<CommentDto> getComments(Long articleId, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<CommentEntity> commentEntities = commentDao.getComments(articleId, pageable); // dao 호출해서 엔티티 리스트 받기
		
		Page<CommentDto> commentDtos = commentEntities.map(CommentDto::new); // Page형 그대로 dto 클래스의 생성자를 이용해서 내용 매핑
		
		return commentDtos;
	}

	@Override
	public List<Long> getCommentNumbers(List<Long> articleIds) {
		// TODO Auto-generated method stub
		List<Long> commentNumbers = new ArrayList<Long>();
		
		// 한 페이지에 출력하는 게시글의 수만큼 반복하여 각 게시글이 가지고 있는 댓글 수 받아와서 list에 저장
		for(int i=0; i<articleIds.size(); i++) {
			commentNumbers.add(commentDao.getCommentNumbers(articleIds.get(i)));
		}

		return commentNumbers;
	}

}
