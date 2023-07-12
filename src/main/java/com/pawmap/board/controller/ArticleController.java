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

import com.pawmap.board.dto.ArticleDto;
import com.pawmap.board.dto.MemberIdentificationDto;
import com.pawmap.board.service.ArticleService;

// 게시판과 관련한 컨트롤러
// 게시글 목록 조회, 삭제
// 게시글 등록, 조회, 수정, 삭제 

@RestController
@RequestMapping("api/board")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	// 게시글 조회 => Dto 리턴
	@GetMapping("/article")
	public ArticleDto getArticle(@RequestParam Long articleId) {
		ArticleDto articleDto = articleService.getArticle(articleId); // 게시글 id로 게시글 서비스 메소드 호출
		
		return articleDto;
	}
	
	// 게시글 등록
	@PostMapping("/article")
	public ResponseEntity<?> postArticle(@RequestBody ArticleDto articleDto, HttpServletRequest request){
		// JwtAuthenticationFilter에서 accessToken이 유효하지 않거나 권한이 맞지 않는 경우 403 코드 리턴
		// accessToken이 유효한 경우 서비스 메소드 호출
		articleService.postArticle(articleDto); // 회원 아이디, 작성 게시글(제목+내용)으로 서비스 메소드 호출
		
		return ResponseEntity.ok().build(); // 응답 코드 200(OK) 리턴
	}
	
	// 게시글 수정
	@PutMapping("/article")
	public ResponseEntity<?> putArticle(@RequestBody ArticleDto articleDto, HttpServletRequest request){
		// JwtAuthenticationFilter에서 accessToken이 유효하지 않거나 권한이 맞지 않는 경우 403 코드 리턴
		// accessToken이 유효한 경우 서비스 메소드 호출
		articleService.putArticle(articleDto); // 회원 아이디, 작성 게시글(제목+내용)으로 서비스 메소드 호출
		
		return ResponseEntity.ok().build(); // 응답 코드 200(OK) 리턴
	}
	
	// 게시글 삭제
	@DeleteMapping("/article")
	public ResponseEntity<?> deleteArticle(@RequestParam("articleId") Long articleId, HttpServletRequest request){
		// JwtAuthenticationFilter에서 accessToken이 유효하지 않거나 권한이 맞지 않는 경우 403 코드 리턴
		// accessToken이 유효한 경우 서비스 메소드 호출
		articleService.deleteArticle(articleId); // 게시글 id, 회원 id로 호출
		
		return ResponseEntity.ok().build(); // 응답 코드 200(OK) 리턴
	}
	
	// 로그인 중인 회원이 조회 중인 게시글의 회원과 일치하는지 확인하고 회원 아이디를 리턴
	// 일치 확인 => 수정, 삭제 메뉴 출력
	// 회원 아이디 => 댓글 컴포넌트로 전달하여 마찬가지로 각 댓글의 작성 회원과 일치하는지 확인
	@GetMapping("/article/member/identification")
	public ResponseEntity<?> getMemberIdentification(@RequestParam Long articleId, HttpServletRequest request) {
		// JwtAuthenticationFilter에서 accessToken이 유효하지 않거나 권한이 맞지 않는 경우 403 코드 리턴
		// accessToken이 유효한 경우 서비스 메소드 호출
		MemberIdentificationDto memberIdentificationDto = articleService.getMemberIdentification(articleId); // 게시글 id로 호출
		
		return ResponseEntity.ok().body(memberIdentificationDto);
	}

	// 게시판에 게시글 목록을 가져오기 위한 메소드
	// 모든 게시글 조회뿐만 아니라 검색 기능 (제목, 내용, 닉네임) 및 회원 자신의 게시글 조회 (아이디) 기능을 위해 파라미터를 받아 판별함 
	@GetMapping("/articles")
	public Page<ArticleDto> getArticles(
			@RequestParam("title") String title,
			@RequestParam("writing") String writing,
			@RequestParam("nickname") String nickname,
			@RequestParam("memberId") String memberId,
			Pageable pageable){
		
		// Page형으로 게시글 dto 리턴
		// 입력 받은 파라미터로 전체 게시글 조회 서비스 메소드 호출
		Page<ArticleDto> articleDtos = articleService.getArticles(title, writing, nickname, memberId, pageable);
		
		return articleDtos;
	}
	
	// 관리자 권한 게시글 삭제 메소드
	@DeleteMapping("/articles")
	public ResponseEntity<?> deleteArticles(@RequestParam List<Long> articleIds, HttpServletRequest request){
		// JwtAuthenticationFilter에서 accessToken이 유효하지 않거나 권한이 맞지 않는 경우 403 코드 리턴
		// accessToken이 유효한 경우 서비스 메소드 호출
		articleService.deleteArticles(articleIds);
		
		return ResponseEntity.ok().build(); // 응답 코드 200(OK) 리턴
	}
	
}
