package com.pawmap.map.controller;

import java.util.List;

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

import com.pawmap.map.dto.BookmarkDto;
import com.pawmap.map.dto.BookmarkFacilityDto;
import com.pawmap.map.service.BookmarkService;

//�ϸ�ũ ���� ��Ʈ�ѷ�
//�ϸ�ũ ���, ��ȸ, ���� 

@RestController
@RequestMapping("api/map")
public class BookmarkController {
	
	@Autowired
	private BookmarkService bookmarkService;
	
	// �� => �ϸ�ũ �߰� �޼ҵ�
	@PostMapping("/bookmark")
	public ResponseEntity<?> postBookmark(@RequestBody BookmarkDto bookmarkDto, HttpServletRequest request){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			// ����� �ִ� accessToken ����
			// JwtAuthenticationFilter ���� ���� ��ȿ�� ��ū�� �ƴ� ��� ���̵�� anonymousUser�̰� ����Ʈ����� �޽��� ����
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			// ��ȿ�� accessToken�� ��� �ϸ�ũ �߰� ���� �޼ҵ� ȣ��
			String data = bookmarkService.postBookmark(bookmarkDto); // ����� ���� �ޱ�
			
			return ResponseEntity.ok().body(data);
		}
	}
	
	// ���������� => �ϸ�ũ ��ȸ �޼ҵ�
	@GetMapping("/bookmarks")
	public ResponseEntity<?> getBookmarks(HttpServletRequest request, Pageable pageable){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			// ����� �ִ� accessToken ����
			// JwtAuthenticationFilter ���� ���� ��ȿ�� ��ū�� �ƴ� ��� ���̵�� anonymousUser�̰� ����Ʈ����� �޽��� ����
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			// ��ȿ�� accessToken�� ��� �ϸ�ũ �߰� ���� �޼ҵ� ȣ��
			Page<BookmarkFacilityDto> bookmarkInfoDtos = bookmarkService.getBookmarks(pageable);
			
			return ResponseEntity.ok().body(bookmarkInfoDtos);
		}
	}
	
	// �ϸ�ũ ���� �޼ҵ�
	@DeleteMapping("/bookmarks")
	public ResponseEntity<?> deleteBookmarks(@RequestParam List<Long> facilityIds, HttpServletRequest request){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			// ����� �ִ� accessToken ����
			// JwtAuthenticationFilter ���� ���� ��ȿ�� ��ū�� �ƴ� ��� ���̵�� anonymousUser�̰� ����Ʈ����� �޽��� ����
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			// ��ȿ�� accessToken�� ��� �ϸ�ũ ���� ���� �޼ҵ� ȣ��
			bookmarkService.deleteBookmarks(facilityIds); // �ü� ���̵� ����Ʈ�� ���� �޼ҵ� ȣ��
			
			return ResponseEntity.ok().build(); // ���� �ڵ� 200(OK) ����
		}
	}

}
