package com.pawmap.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Table(name = "emd")
@Entity
public class EmdEntity {
	
	@Id
	private Long emd_id;
	private String emd_name;

}
