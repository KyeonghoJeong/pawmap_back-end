package com.pawmap.bookmark.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.bookmark.dto.BookmarkDto;
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
			System.out.println("error");
			return ResponseEntity.ok().build();
		}else {
			BookmarkDto bookmarkDto = new BookmarkDto();

			bookmarkDto.setMemberId(SecurityContextHolder.getContext().getAuthentication().getName());
			bookmarkDto.setFacilityId(requestDto.getFacilityId());
			
			String result = bookmarkService.addBookmark(bookmarkDto);
			
			return ResponseEntity.ok().body(result);
		}
	}
	
}
