package com.pawmap.facility.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Table(name = "emd")
@Entity
public class EmdEntity {
	
	@Id
	@Column(name = "emdid")
	private Long emdId;
	
	@Column(name = "sigunguid")
	private Long sigunguId;
	
	@Column(name = "emdname")
	private String emdName;

}