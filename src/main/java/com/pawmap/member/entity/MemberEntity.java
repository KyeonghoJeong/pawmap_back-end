package com.pawmap.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name="memberinfo")
@Getter
public class MemberEntity {

	@Id
	@Column(name = "memberid")
	private String memberId;
	
	@Column(name = "pw")
	private String pw;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "email")
	private String email;
	
	@Column(name = "auth")
	private String role;
	
	public MemberEntity() {
		
	}

	public MemberEntity(String memberId, String pw, String nickname, String email, String role) {
		this.memberId = memberId;
		this.pw = pw;
		this.nickname = nickname;
		this.email = email;
		this.role = role;
	}
	
}
