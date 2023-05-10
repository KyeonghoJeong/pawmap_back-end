package com.pawmap.facility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pawmap.facility.entity.SidoEntity;

@Repository
public interface SidoRepository extends JpaRepository<SidoEntity, Long>{

}