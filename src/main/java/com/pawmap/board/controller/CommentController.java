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

// �Խñ� �� ��� ��ɿ� ������ ��Ʈ�ѷ�
// ��� ���, ��ȸ, ����, ����
// �Խñ� �� ��� �� ǥ��

@RestController
@RequestMapping("api/board/article")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	// ��� ��� �޼ҵ�
	@PostMapping("/comment")
	public ResponseEntity<?> postComment(@RequestBody CommentDto commentDto, HttpServletRequest request){
		// JwtAuthenticationFilter���� accessToken�� ��ȿ���� �ʰų� ������ ���� �ʴ� ��� 403 �ڵ� ����
		// accessToken�� ��ȿ�� ��� ���� �޼ҵ� ȣ��
		commentService.postComment(commentDto); // �Խñ� id, ȸ�� id, ��� �������� ��� ��� ���� �޼ҵ� ȣ��
		
		return ResponseEntity.ok().build(); // ���� �ڵ� 200(OK) ����
	}
	
	// ��� ���� �޼ҵ�
	@PutMapping("/comment")
	public ResponseEntity<?> putComment(@RequestBody CommentDto commentDto, HttpServletRequest request){
		// JwtAuthenticationFilter���� accessToken�� ��ȿ���� �ʰų� ������ ���� �ʴ� ��� 403 �ڵ� ����
		// accessToken�� ��ȿ�� ��� ���� �޼ҵ� ȣ��
		commentService.putComment(commentDto);
		
		return ResponseEntity.ok().build(); // ���� �ڵ� 200(OK) ����
	}
	
	// ��� ���� �޼ҵ�
	@DeleteMapping("/comment")
	public ResponseEntity<?> deleteComment(@RequestParam("cmtId") Long cmtId, HttpServletRequest request){
		// JwtAuthenticationFilter���� accessToken�� ��ȿ���� �ʰų� ������ ���� �ʴ� ��� 403 �ڵ� ����
		// accessToken�� ��ȿ�� ��� ���� �޼ҵ� ȣ��
		commentService.deleteComment(cmtId);
		
		return ResponseEntity.ok().build(); // ���� �ڵ� 200(OK) ����
	}
	
	// �� �������� �Խñ۵��� id�� �Ķ���ͷ� �޾Ƽ� �� �Խñ��� ������ �ִ� ����� ���� �����ϴ� �޼ҵ�
	@GetMapping("/comment/numbers")
	public List<Long> getCommentNumbers(@RequestParam("articleIds") List<Long> articleIds){
		List<Long> commentNumbers = commentService.getCommentNumbers(articleIds); // �� �������� �Խñ� id ��� ����Ʈ�� ����

		return commentNumbers;
	}
	
	// Ư�� �Խñ��� ��� ��� ��ȸ �޼ҵ�
	@GetMapping("/comments")
	public Page<CommentDto> getComments(@RequestParam Long articleId, Pageable pageable){
		Page<CommentDto> commentDtos = commentService.getComments(articleId, pageable); // �Խñ� id�� ���� �޼ҵ� ȣ��
		
		return commentDtos;
	}
	
}
