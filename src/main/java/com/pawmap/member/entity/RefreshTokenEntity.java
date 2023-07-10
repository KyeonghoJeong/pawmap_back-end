package com.pawmap.member.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name="refreshtoken")
@Getter
public class RefreshTokenEntity {

	@Id
	@Column(name="refreshtokenid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "refreshtokensequence")
    @SequenceGenerator(name = "refreshtokensequence", sequenceName = "refreshtokensequence", allocationSize = 1)
	private Long refreshTokenId;
	
	@Column(name="memberid")
	private String memberId;
	
	@Column(name="refreshtoken")
	private String refreshToken;
	
	@Column(name="expirationtime")
	private Timestamp expirationTime;
	
	public RefreshTokenEntity() {
		
	}

	public RefreshTokenEntity(Long refreshTokenId, String memberId, String refreshToken, Timestamp expirationTime) {
		this.refreshTokenId = refreshTokenId;
		this.memberId = memberId;
		this.refreshToken = refreshToken;
		this.expirationTime = expirationTime;
	}
	
}