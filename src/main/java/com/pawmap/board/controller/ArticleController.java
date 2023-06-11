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

@RestController
@RequestMapping("api")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ArticleRepository articleRepository;

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
	
	@DeleteMapping("/board/articles")
	public ResponseEntity<?> deleteArticles(HttpServletRequest request, @RequestBody List<Long> articleIds){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("Invalid");
		}else {
			articleService.deleteArticles(articleIds);
			
			return ResponseEntity.ok().body("Deleted");
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
