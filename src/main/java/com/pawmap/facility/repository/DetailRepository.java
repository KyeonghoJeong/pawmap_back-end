package com.pawmap.facility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pawmap.facility.entity.FacilityEntity;

@Repository
public interface DetailRepository extends JpaRepository<FacilityEntity, Long> {

}
