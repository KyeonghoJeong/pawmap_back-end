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

//북마크 관련 컨트롤러
//북마크 등록, 조회, 제거 

@RestController
@RequestMapping("api/map")
public class BookmarkController {
	
	@Autowired
	private BookmarkService bookmarkService;
	
	// 맵 => 북마크 추가 메소드
	@PostMapping("/bookmark")
	public ResponseEntity<?> postBookmark(@RequestBody BookmarkDto bookmarkDto, HttpServletRequest request){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			// 헤더에 있는 accessToken 인증
			// JwtAuthenticationFilter 인증 이후 유효한 토큰이 아닌 경우 아이디는 anonymousUser이고 프론트엔드로 메시지 보냄
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			// 유효한 accessToken인 경우 북마크 추가 서비스 메소드 호출
			String data = bookmarkService.postBookmark(bookmarkDto); // 결과값 리턴 받기
			
			return ResponseEntity.ok().body(data);
		}
	}
	
	// 마이페이지 => 북마크 조회 메소드
	@GetMapping("/bookmarks")
	public ResponseEntity<?> getBookmarks(HttpServletRequest request, Pageable pageable){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			// 헤더에 있는 accessToken 인증
			// JwtAuthenticationFilter 인증 이후 유효한 토큰이 아닌 경우 아이디는 anonymousUser이고 프론트엔드로 메시지 보냄
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			// 유효한 accessToken인 경우 북마크 추가 서비스 메소드 호출
			Page<BookmarkFacilityDto> bookmarkInfoDtos = bookmarkService.getBookmarks(pageable);
			
			return ResponseEntity.ok().body(bookmarkInfoDtos);
		}
	}
	
	// 북마크 삭제 메소드
	@DeleteMapping("/bookmarks")
	public ResponseEntity<?> deleteBookmarks(@RequestParam List<Long> facilityIds, HttpServletRequest request){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			// 헤더에 있는 accessToken 인증
			// JwtAuthenticationFilter 인증 이후 유효한 토큰이 아닌 경우 아이디는 anonymousUser이고 프론트엔드로 메시지 보냄
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			// 유효한 accessToken인 경우 북마크 삭제 서비스 메소드 호출
			bookmarkService.deleteBookmarks(facilityIds); // 시설 아이디 리스트로 삭제 메소드 호출
			
			return ResponseEntity.ok().build(); // 응답 코드 200(OK) 리턴
		}
	}

}
