package com.pawmap.facility.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Table(name = "sido")
@Entity
public class SidoEntity {
	
	@Id
	@Column(name = "sidoid")
	private Long sidoId;
	
	@Column(name = "sidoname")
	private String sidoName;

}
