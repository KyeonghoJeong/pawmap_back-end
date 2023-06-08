package com.pawmap.board.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.board.dto.CommentDto;
import com.pawmap.board.service.CommentService;

@RestController
@RequestMapping("api/board/article")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	@PostMapping("/comment")
	public ResponseEntity<?> postComment(HttpServletRequest request, @RequestBody Map<String, String> commentInfo){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("Invalid");
		}else {
			Long articleId = Long.parseLong(commentInfo.get("articleId"));
			String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
			String writing = commentInfo.get("writing");
			
			commentService.postComment(articleId, memberId, writing);
			
			return ResponseEntity.ok().body("Success");
		}
	}
	
	@GetMapping("/comments")
	public Page<CommentDto> getComments(@RequestParam Long articleId, Pageable pageable){
		Page<CommentDto> commentDtos = commentService.getComments(articleId, pageable);
		
		return commentDtos;
	}
	
}
