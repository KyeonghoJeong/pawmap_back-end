package com.pawmap.board.dto;

import java.text.SimpleDateFormat;

import com.pawmap.board.entity.CommentEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentDto {

	private Long cmtId;
	
	private Long articleId;
	
	private String nickname;
	
	private String writing;
	
	private String postDate;

	public CommentDto(CommentEntity commentEntity) {
		this.cmtId = commentEntity.getCmtId();
		this.articleId = commentEntity.getArticleId();
		this.nickname = commentEntity.getNickname();
		this.writing = commentEntity.getWriting();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedTime = sdf.format(commentEntity.getPostDate());
		this.postDate = formattedTime;
	}

}
