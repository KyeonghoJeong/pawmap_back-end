package com.pawmap.map.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Table(name = "sigungu")
@Entity
public class SigunguEntity {
	
	@Id
	@Column(name = "sigunguid")
	private Long sigunguId;
	
	@Column(name = "sidoid")
	private Long sidoId;
	
	@Column(name = "sigunguname")
	private String sigunguName;

}