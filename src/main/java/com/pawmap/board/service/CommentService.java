package com.pawmap.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.board.dto.CommentDto;

public interface CommentService {

	void postComment(Map<String, String> commentData, String memberId);
	
	void putComment(Map<String, String> commentData);

	void deleteComment(Long cmtId);
	
	Page<CommentDto> getComments(Long articleId, Pageable pageable);

	List<Long> getCommentNumbers(List<Long> articleIds);

}
