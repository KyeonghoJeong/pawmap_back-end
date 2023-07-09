package com.pawmap.map.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Getter
@Table(name="bookmark")
public class BookmarkEntity {

	@Id
	@Column(name="bookmarkid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookmarksequence")
    @SequenceGenerator(name = "bookmarksequence", sequenceName = "bookmarksequence", allocationSize = 1)
	private Long bookmarkId;
	
	@Column(name="memberid")
	private String memberId;
	
	@Column(name="facilityid")
	private Long facilityId;
	
}
