package com.pawmap.board.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.board.entity.CommentEntity;

public interface CommentDao {

	void putComment(CommentEntity commentEntity);

	Page<CommentEntity> getComments(Long articleId, Pageable pageable);

	void deleteComment(Long cmtId);

	void putComment(Long cmtId, String writing);

	Long getCommentNumbers(Long articleId);

}
