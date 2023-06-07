package com.pawmap.board.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.board.dto.ArticleDto;
import com.pawmap.board.service.ArticleService;

@RestController
@RequestMapping("api")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;

	@GetMapping("/board/articles")
	public Page<ArticleDto> getArticles(Pageable pageable){
		Page<ArticleDto> articleDtos = articleService.getArticles(pageable);
		
		return articleDtos;
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
			String result = articleService.deleteArticle(articleId, memberId);
			
			return ResponseEntity.ok().body(result);
		}
	}
	
}