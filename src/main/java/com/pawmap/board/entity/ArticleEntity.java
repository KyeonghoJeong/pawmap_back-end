package com.pawmap.board.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name="article")
@Getter
public class ArticleEntity {

	@Id
	@Column(name="articleid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "articlesequence")
    @SequenceGenerator(name = "articlesequence", sequenceName = "articlesequence", allocationSize = 1)
	private Long articleId;
	
	@Column(name="memberid")
	private String memberId;
	
	@Column(name="nickname")
	private String nickname;
	
	@Column(name="title")
	private String title;
	
	@Column(name="writing")
	private String writing;
	
	@Column(name="postdate")
	private Date postDate;
	
	public ArticleEntity() {
		
	}
	
	public ArticleEntity(Long articleId, String memberId, String nickname, String title, String writing,
			Date postDate) {
		this.articleId = articleId;
		this.memberId = memberId;
		this.nickname = nickname;
		this.title = title;
		this.writing = writing;
		this.postDate = postDate;
	}
	
}
