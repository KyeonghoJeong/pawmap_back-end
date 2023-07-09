package com.pawmap.board.dto;

import java.text.SimpleDateFormat;

import com.pawmap.board.entity.ArticleEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 게시글 entity와 데이터 구조 동일

@Getter
@Setter
@AllArgsConstructor
public class ArticleDto {
	
	private Long articleId;
	private String memberId;
	private String nickname;
	private String title;
	private String writing;
	private String postDate;
	
	public ArticleDto(){
		
	}
	
	public ArticleDto(ArticleEntity articleEntity) {
		this.articleId = articleEntity.getArticleId();
		this.memberId = articleEntity.getMemberId();
		this.nickname = articleEntity.getNickname();
		this.title = articleEntity.getTitle();
		this.writing = articleEntity.getWriting();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedTime = sdf.format(articleEntity.getPostDate());
		this.postDate = formattedTime;
	}

}
