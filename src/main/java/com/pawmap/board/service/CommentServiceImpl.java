package com.pawmap.board.service;

import java.util.Calendar;
import java.util.Date;

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
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private CommentDao commentDao;
	
	@Override
	public void postComment(Long articleId, String memberId, String writing) {
		// TODO Auto-generated method stub
		MemberEntity memberEntity = memberDao.getMember(memberId);
		String nickname = memberEntity.getNickname();

		Date currentTime = Calendar.getInstance().getTime();
		
		CommentEntity commentEntity = new CommentEntity(null, articleId, nickname, writing, currentTime);
	
		commentDao.putComment(commentEntity);
	}

	@Override
	public Page<CommentDto> getComments(Long articleId, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<CommentEntity> commentEntities = commentDao.getComments(articleId, pageable);
		
		Page<CommentDto> commentDtos = commentEntities.map(CommentDto::new);
		
		return commentDtos;
	}

}
