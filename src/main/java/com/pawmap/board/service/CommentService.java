package com.pawmap.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.board.dto.CommentDto;

public interface CommentService {

	void postComment(Long articleId, String memberId, String writing);

	Page<CommentDto> getComments(Long articleId, Pageable pageable);

}
