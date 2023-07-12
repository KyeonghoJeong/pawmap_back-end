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

// �Խ��ǰ� ������ ��Ʈ�ѷ�
// �Խñ� ��� ��ȸ, ����
// �Խñ� ���, ��ȸ, ����, ���� 

@RestController
@RequestMapping("api/board")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	// �Խñ� ��ȸ => Dto ����
	@GetMapping("/article")
	public ArticleDto getArticle(@RequestParam Long articleId) {
		ArticleDto articleDto = articleService.getArticle(articleId); // �Խñ� id�� �Խñ� ���� �޼ҵ� ȣ��
		
		return articleDto;
	}
	
	// �Խñ� ���
	@PostMapping("/article")
	public ResponseEntity<?> postArticle(@RequestBody ArticleDto articleDto, HttpServletRequest request){
		// JwtAuthenticationFilter���� accessToken�� ��ȿ���� �ʰų� ������ ���� �ʴ� ��� 403 �ڵ� ����
		// accessToken�� ��ȿ�� ��� ���� �޼ҵ� ȣ��
		articleService.postArticle(articleDto); // ȸ�� ���̵�, �ۼ� �Խñ�(����+����)���� ���� �޼ҵ� ȣ��
		
		return ResponseEntity.ok().build(); // ���� �ڵ� 200(OK) ����
	}
	
	// �Խñ� ����
	@PutMapping("/article")
	public ResponseEntity<?> putArticle(@RequestBody ArticleDto articleDto, HttpServletRequest request){
		// JwtAuthenticationFilter���� accessToken�� ��ȿ���� �ʰų� ������ ���� �ʴ� ��� 403 �ڵ� ����
		// accessToken�� ��ȿ�� ��� ���� �޼ҵ� ȣ��
		articleService.putArticle(articleDto); // ȸ�� ���̵�, �ۼ� �Խñ�(����+����)���� ���� �޼ҵ� ȣ��
		
		return ResponseEntity.ok().build(); // ���� �ڵ� 200(OK) ����
	}
	
	// �Խñ� ����
	@DeleteMapping("/article")
	public ResponseEntity<?> deleteArticle(@RequestParam("articleId") Long articleId, HttpServletRequest request){
		// JwtAuthenticationFilter���� accessToken�� ��ȿ���� �ʰų� ������ ���� �ʴ� ��� 403 �ڵ� ����
		// accessToken�� ��ȿ�� ��� ���� �޼ҵ� ȣ��
		articleService.deleteArticle(articleId); // �Խñ� id, ȸ�� id�� ȣ��
		
		return ResponseEntity.ok().build(); // ���� �ڵ� 200(OK) ����
	}
	
	// �α��� ���� ȸ���� ��ȸ ���� �Խñ��� ȸ���� ��ġ�ϴ��� Ȯ���ϰ� ȸ�� ���̵� ����
	// ��ġ Ȯ�� => ����, ���� �޴� ���
	// ȸ�� ���̵� => ��� ������Ʈ�� �����Ͽ� ���������� �� ����� �ۼ� ȸ���� ��ġ�ϴ��� Ȯ��
	@GetMapping("/article/member/identification")
	public ResponseEntity<?> getMemberIdentification(@RequestParam Long articleId, HttpServletRequest request) {
		// JwtAuthenticationFilter���� accessToken�� ��ȿ���� �ʰų� ������ ���� �ʴ� ��� 403 �ڵ� ����
		// accessToken�� ��ȿ�� ��� ���� �޼ҵ� ȣ��
		MemberIdentificationDto memberIdentificationDto = articleService.getMemberIdentification(articleId); // �Խñ� id�� ȣ��
		
		return ResponseEntity.ok().body(memberIdentificationDto);
	}

	// �Խ��ǿ� �Խñ� ����� �������� ���� �޼ҵ�
	// ��� �Խñ� ��ȸ�Ӹ� �ƴ϶� �˻� ��� (����, ����, �г���) �� ȸ�� �ڽ��� �Խñ� ��ȸ (���̵�) ����� ���� �Ķ���͸� �޾� �Ǻ��� 
	@GetMapping("/articles")
	public Page<ArticleDto> getArticles(
			@RequestParam("title") String title,
			@RequestParam("writing") String writing,
			@RequestParam("nickname") String nickname,
			@RequestParam("memberId") String memberId,
			Pageable pageable){
		
		// Page������ �Խñ� dto ����
		// �Է� ���� �Ķ���ͷ� ��ü �Խñ� ��ȸ ���� �޼ҵ� ȣ��
		Page<ArticleDto> articleDtos = articleService.getArticles(title, writing, nickname, memberId, pageable);
		
		return articleDtos;
	}
	
	// ������ ���� �Խñ� ���� �޼ҵ�
	@DeleteMapping("/articles")
	public ResponseEntity<?> deleteArticles(@RequestParam List<Long> articleIds, HttpServletRequest request){
		// JwtAuthenticationFilter���� accessToken�� ��ȿ���� �ʰų� ������ ���� �ʴ� ��� 403 �ڵ� ����
		// accessToken�� ��ȿ�� ��� ���� �޼ҵ� ȣ��
		articleService.deleteArticles(articleIds);
		
		return ResponseEntity.ok().build(); // ���� �ڵ� 200(OK) ����
	}
	
}
