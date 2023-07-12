package com.pawmap.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.board.dto.CommentDto;

public interface CommentService {

	void postComment(CommentDto commentDto);
	
	void putComment(CommentDto commentDto);

	void deleteComment(Long cmtId);
	
	List<Long> getCommentNumbers(List<Long> articleIds);
	
	Page<CommentDto> getComments(Long articleId, Pageable pageable);	

}
