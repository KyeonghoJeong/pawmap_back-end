package com.pawmap.board.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.board.entity.CommentEntity;

public interface CommentDao {

	void postComment(CommentEntity commentEntity);
	
	void putComment(Long cmtId, String writing);

	void deleteComment(Long cmtId);
	
	Page<CommentEntity> getComments(Long articleId, Pageable pageable);

	Long getCommentNumbers(Long articleId);

}
