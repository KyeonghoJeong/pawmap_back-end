package com.pawmap.bookmark.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.bookmark.dto.BookmarkInfoDto;
import com.pawmap.bookmark.service.BookmarkService;

// 북마크 관련 컨트롤러
// 북마크 등록, 조회, 제거 

@RestController
@RequestMapping("api")
public class BookmarkController {
	
	@Autowired
	private BookmarkService bookmarkService;

	// 북마크 추가 메소드
	@PostMapping("/bookmark")
	public ResponseEntity<?> addBookmark(HttpServletRequest request, @RequestBody Map<String, Long> facility){
		// 헤더에 있는 accessToken 인증
		// JwtAuthenticationFilter 인증 이후 유효한 토큰이 아닌 경우 아이디는 anonymousUser이고 프론트엔드로 메시지 보냄
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			// 유효한 accessToken인 경우 북마크 추가 서비스 메소드 호출
			
			Long facilityid = facility.get("facilityId");
			
			String result = bookmarkService.addBookmark(facilityid);
			
			return ResponseEntity.ok().body(result);
		}
	}
	
	@GetMapping("/bookmarks")
	public ResponseEntity<?> getInfo(HttpServletRequest request, Pageable pageable){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("Invalid");
		}else {
			Page<BookmarkInfoDto> bookmarkInfoDtos = bookmarkService.getBookmarkInfo(SecurityContextHolder.getContext().getAuthentication().getName(), pageable);
			
			return ResponseEntity.ok().body(bookmarkInfoDtos);
		}
	}
	
	@DeleteMapping("/bookmark")
	public ResponseEntity<?> deleteBookmark(HttpServletRequest request, @RequestBody List<Long> facilityId){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("Invalid");
		}else {
			String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
			
			bookmarkService.deleteBookmark(memberId, facilityId);
			
			return ResponseEntity.ok().body("Deleted");
		}
	}
	
	@DeleteMapping("/bookmarks")
	public ResponseEntity<?> deleteBookmarks(@RequestParam("memberId") String memberId){
		bookmarkService.deleteBookmarks(memberId);
		
		return ResponseEntity.ok().body("Success");
	}
	
}
