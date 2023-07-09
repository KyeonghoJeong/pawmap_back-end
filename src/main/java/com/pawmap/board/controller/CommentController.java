package com.pawmap.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public ResponseEntity<?> postComment(@RequestBody Map<String, String> commentData, HttpServletRequest request){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			// 헤더에 있는 accessToken 인증
			// JwtAuthenticationFilter 인증 이후 유효한 토큰이 아닌 경우 아이디는 anonymousUser이고 프론트엔드로 메시지 보냄
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			// 유효한 accessToken인 경우 서비스 메소드 호출
			String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder에서 회원 아이디 가져오기 
			
			commentService.postComment(commentData, memberId); // 게시글 id, 회원 id, 댓글 내용으로 댓글 등록 서비스 메소드 호출
			
			return ResponseEntity.ok().build(); // 응답 코드 200(OK) 리턴
		}
	}
	
	// 댓글 수정 메소드
	@PutMapping("/comment")
	public ResponseEntity<?> putComment(@RequestBody Map<String, String> commentData, HttpServletRequest request){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			// 헤더에 있는 accessToken 인증
			// JwtAuthenticationFilter 인증 이후 유효한 토큰이 아닌 경우 아이디는 anonymousUser이고 프론트엔드로 메시지 보냄
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			// 유효한 accessToken인 경우 서비스 메소드 호출
			commentService.putComment(commentData);
			
			return ResponseEntity.ok().build(); // 응답 코드 200(OK) 리턴
		}
	}
	
	// 댓글 삭제 메소드
	@DeleteMapping("/comment")
	public ResponseEntity<?> deleteComment(@RequestParam("cmtId") Long cmtId, HttpServletRequest request){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			// 헤더에 있는 accessToken 인증
			// JwtAuthenticationFilter 인증 이후 유효한 토큰이 아닌 경우 아이디는 anonymousUser이고 프론트엔드로 메시지 보냄
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			// 유효한 accessToken인 경우 서비스 메소드 호출
			commentService.deleteComment(cmtId);
			
			return ResponseEntity.ok().build(); // 응답 코드 200(OK) 리턴
		}
	}
	
	// 특정 게시글의 모든 댓글 조회 메소드
	@GetMapping("/comments")
	public Page<CommentDto> getComments(@RequestParam Long articleId, Pageable pageable){
		Page<CommentDto> commentDtos = commentService.getComments(articleId, pageable); // 게시글 id로 서비스 메소드 호출
		
		return commentDtos;
	}
	
	// 한 페이지의 게시글들의 id를 파라미터로 받아서 각 게시글이 가지고 있는 댓글의 수를 리턴하는 메소드
	@GetMapping("/comment/numbers")
	public List<Long> getCommentNumbers(@RequestParam("articleIds") List<Long> articleIds){
		List<Long> commentNumbers = commentService.getCommentNumbers(articleIds); // 한 페이지의 게시글 id 모두 리스트로 전달

		return commentNumbers;
	}
	
}
