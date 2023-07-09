package com.pawmap.map.dto;

import lombok.Getter;
import lombok.Setter;

// 북마크 추가 시 사용하며 북마크 엔티티와 같은 데이터 구조

@Getter
@Setter
public class BookmarkDto {

	private Long bookmarkId;
	private String memberId;
	private Long facilityId;
	
}
