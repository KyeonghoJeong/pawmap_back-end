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
		
		CommentEntity commentEntity = new CommentEntity(null, articleId, memberId, nickname, writing, currentTime);
	
		commentDao.putComment(commentEntity);
	}

	@Override
	public Page<CommentDto> getComments(Long articleId, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<CommentEntity> commentEntities = commentDao.getComments(articleId, pageable);
		
		Page<CommentDto> commentDtos = commentEntities.map(CommentDto::new);
		
		return commentDtos;
	}

	@Override
	public void deleteComment(Long cmtId) {
		// TODO Auto-generated method stub
		commentDao.deleteComment(cmtId);
	}

	@Override
	public void putComment(Map<String, String> cmtInfo) {
		// TODO Auto-generated method stub
		Long cmtId = Long.parseLong(cmtInfo.get("cmtId"));
		String writing = cmtInfo.get("writing");
		
		commentDao.putComment(cmtId, writing);
	}

	@Override
	public List<Long> getCommentNumbers(List<Long> articleIds) {
		// TODO Auto-generated method stub
		List<Long> commentNumbers = new ArrayList<Long>();
		
		for(int i=0; i<articleIds.size(); i++) {
			commentNumbers.add(commentDao.getCommentNumbers(articleIds.get(i)));
		}

		return commentNumbers;
	}

}
