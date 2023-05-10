package com.pawmap.facility.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pawmap.facility.entity.SigunguEntity;

@Repository
public interface SigunguRepository extends JpaRepository<SigunguEntity, Long>{

	List<SigunguEntity> findBySidoId(Long sidoId);

}