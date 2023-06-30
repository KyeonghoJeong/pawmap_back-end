package com.pawmap.board.controller;

import java.util.HashMap;
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

import com.pawmap.board.dto.ArticleDto;
import com.pawmap.board.repository.ArticleRepository;
import com.pawmap.board.service.ArticleService;

// 게시판과 관련한 컨트롤러
// 게시글 목록 조회, 삭제
// 게시글 등록, 조회, 수정, 삭제 

@RestController
@RequestMapping("api")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ArticleRepository articleRepository;

	// 게시판에 게시글 목록을 가져오기 위한 메소드
	// 모든 게시글 조회뿐만 아니라 검색 기능 (제목, 내용, 닉네임) 및 회원 자신의 게시글 조회 (아이디) 기능을 위해 파라미터를 받아 판별함 
	@GetMapping("/board/articles")
	public Page<ArticleDto> getArticles(
			@RequestParam("title") String title,
			@RequestParam("writing") String writing,
			@RequestParam("nickname") String nickname,
			@RequestParam("memberId") String memberId,
			Pageable pageable){
		
		Page<ArticleDto> articleDtos = articleService.getArticles(title, writing, nickname, memberId, pageable);
		
		return articleDtos;
	}
	
	// 관리자 권한 게시글 삭제 메소드
	@DeleteMapping("/board/articles")
	public ResponseEntity<?> deleteArticles(HttpServletRequest request, @RequestBody List<Long> articleIds){
		// 헤더로 accessToken을 받고 RequestBody로 삭제할 게시글의 ID들을 받음
		
		// 전달 받은 accessToken에 대한 JwtAuthenticationFilter의 결과로 받은 Authentication 객체의 회원 아이디가 유효하지 앟은 경우 invalidAccessToken 리턴
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			// 유효한 경우 게시글 삭제 서비스 메소드 호출
			articleService.deleteArticles(articleIds);
			
			// 성공 시 Success 리턴
			return ResponseEntity.ok().body("Success");
		}
	}
	
	@GetMapping("/board/article/membercheck")
	public ResponseEntity<?> checkMember(HttpServletRequest request, @RequestParam Long articleId) {
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("Invalid");
		}else {
			String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
			Long count = articleRepository.getCount(articleId, memberId);
			
			Map<String, Object> result = new HashMap<>();
			result.put("memberId", memberId);
			result.put("count", count);
			
			return ResponseEntity.ok().body(result);
		}
	}
	
	@PostMapping("/board/article")
	public ResponseEntity<?> postArticle(HttpServletRequest request, @RequestBody Map<String, String> article){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("Invalid");
		}else {
			String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
			articleService.postArticle(memberId, article);
			
			return ResponseEntity.ok().body("Success");
		}
	}
	
	@GetMapping("/board/article")
	public ArticleDto getArticle(@RequestParam Long articleId) {
		ArticleDto articleDto = articleService.getArticles(articleId);
		
		return articleDto;
	}
	
	@DeleteMapping("/board/article")
	public ResponseEntity<?> deleteArticle(HttpServletRequest request, @RequestParam("articleId") Long articleId){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("Invalid");
		}else {
			String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
			articleService.deleteArticle(articleId, memberId);
			
			return ResponseEntity.ok().body("Success");
		}
	}
	
	@PutMapping("/board/article")
	public ResponseEntity<?> putArticle(HttpServletRequest request, @RequestBody Map<String, String> article){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("Invalid");
		}else {
			String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
			articleService.putArticle(memberId, article);
			
			return ResponseEntity.ok().body("Success");
		}
	}
	
}
