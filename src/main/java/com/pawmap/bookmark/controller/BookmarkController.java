package com.pawmap.bookmark.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.bookmark.dto.BookmarkDto;
import com.pawmap.bookmark.dto.BookmarkInfoDto;
import com.pawmap.bookmark.dto.RequestDto;
import com.pawmap.bookmark.service.BookmarkService;

@RestController
@RequestMapping("api")
public class BookmarkController {
	
	@Autowired
	private BookmarkService bookmarkService;

	@PostMapping("/bookmark")
	public ResponseEntity<?> addBookmark(HttpServletRequest request, @RequestBody RequestDto requestDto){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("Invalid");
		}else {
			BookmarkDto bookmarkDto = new BookmarkDto();
			
			bookmarkDto.setMemberId(SecurityContextHolder.getContext().getAuthentication().getName());
			bookmarkDto.setFacilityId(requestDto.getFacilityId());
			
			String result = bookmarkService.addBookmark(bookmarkDto);
			
			return ResponseEntity.ok().body(result);
		}
	}
	
	@GetMapping("/bookmark")
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
	
}
