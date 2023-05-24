package com.pawmap.user.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name="userinfo")
@Getter
public class UserEntity {

	private String userId;
	private String pw;
	private String nickname;
	private String email;
	
}
