package com.pawmap.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Table(name = "sido")
@Entity
public class SidoEntity {
	
	@Id
	private Long sido_id;
	private String sido_name;

}
