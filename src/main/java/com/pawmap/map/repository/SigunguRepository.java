package com.pawmap.map.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pawmap.map.entity.SigunguEntity;

@Repository
public interface SigunguRepository extends JpaRepository<SigunguEntity, Long>{

	// �õ� id�� �ñ��� ã��
	List<SigunguEntity> findBySidoId(Long sidoId);

}