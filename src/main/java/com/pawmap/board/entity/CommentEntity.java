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
@Table(name="cmt")
@Getter
public class CommentEntity {

	@Id
	@Column(name="cmtid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cmtsequence")
    @SequenceGenerator(name = "cmtsequence", sequenceName = "cmtsequence", allocationSize = 1)
	private Long cmtId;
	
	@Column(name="articleid")
	private Long articleId;
	
	@Column(name="nickname")
	private String nickname;
	
	@Column(name="writing")
	private String writing;
	
	@Column(name="postdate")
	private Date postDate;

	public CommentEntity() {
		
	}

	public CommentEntity(Long cmtId, Long articleId, String nickname, String writing, Date postDate) {
		this.cmtId = cmtId;
		this.articleId = articleId;
		this.nickname = nickname;
		this.writing = writing;
		this.postDate = postDate;
	}
	
}
