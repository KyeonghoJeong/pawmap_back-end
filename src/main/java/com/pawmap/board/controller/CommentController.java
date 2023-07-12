package com.pawmap.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.board.dto.CommentDto;
import com.pawmap.board.service.CommentService;

// 게시글 별 댓글 기능에 관련한 컨트롤러
// 댓글 등록, 조회, 삭제, 수정
// 게시글 별 댓글 수 표시

@RestController
@RequestMapping("api/board/article")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	// 댓글 등록 메소드
	@PostMapping("/comment")
	public ResponseEntity<?> postComment(@RequestBody CommentDto commentDto, HttpServletRequest request){
		// JwtAuthenticationFilter에서 accessToken이 유효하지 않거나 권한이 맞지 않는 경우 403 코드 리턴
		// accessToken이 유효한 경우 서비스 메소드 호출
		commentService.postComment(commentDto); // 게시글 id, 회원 id, 댓글 내용으로 댓글 등록 서비스 메소드 호출
		
		return ResponseEntity.ok().build(); // 응답 코드 200(OK) 리턴
	}
	
	// 댓글 수정 메소드
	@PutMapping("/comment")
	public ResponseEntity<?> putComment(@RequestBody CommentDto commentDto, HttpServletRequest request){
		// JwtAuthenticationFilter에서 accessToken이 유효하지 않거나 권한이 맞지 않는 경우 403 코드 리턴
		// accessToken이 유효한 경우 서비스 메소드 호출
		commentService.putComment(commentDto);
		
		return ResponseEntity.ok().build(); // 응답 코드 200(OK) 리턴
	}
	
	// 댓글 삭제 메소드
	@DeleteMapping("/comment")
	public ResponseEntity<?> deleteComment(@RequestParam("cmtId") Long cmtId, HttpServletRequest request){
		// JwtAuthenticationFilter에서 accessToken이 유효하지 않거나 권한이 맞지 않는 경우 403 코드 리턴
		// accessToken이 유효한 경우 서비스 메소드 호출
		commentService.deleteComment(cmtId);
		
		return ResponseEntity.ok().build(); // 응답 코드 200(OK) 리턴
	}
	
	// 한 페이지의 게시글들의 id를 파라미터로 받아서 각 게시글이 가지고 있는 댓글의 수를 리턴하는 메소드
	@GetMapping("/comment/numbers")
	public List<Long> getCommentNumbers(@RequestParam("articleIds") List<Long> articleIds){
		List<Long> commentNumbers = commentService.getCommentNumbers(articleIds); // 한 페이지의 게시글 id 모두 리스트로 전달

		return commentNumbers;
	}
	
	// 특정 게시글의 모든 댓글 조회 메소드
	@GetMapping("/comments")
	public Page<CommentDto> getComments(@RequestParam Long articleId, Pageable pageable){
		Page<CommentDto> commentDtos = commentService.getComments(articleId, pageable); // 게시글 id로 서비스 메소드 호출
		
		return commentDtos;
	}
	
}
