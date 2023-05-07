package com.pawmap.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Table(name = "sigungu")
@Entity
public class SigunguEntity {
	
	@Id
	private Long sigungu_id;
	private String sigungu_name;

}
