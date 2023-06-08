package com.pawmap.board.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.pawmap.board.entity.CommentEntity;
import com.pawmap.board.repository.CommentRepository;

@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public void putComment(CommentEntity commentEntity) {
		// TODO Auto-generated method stub
		commentRepository.save(commentEntity);
	}

	@Override
	public Page<CommentEntity> getComments(Long articleId, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<CommentEntity> commentEntities = commentRepository.findByArticleIdOrderByPostDateAsc(articleId, pageable);
		
		return commentEntities;
	}

}
