package com.pawmap.member.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawmap.member.entity.MemberEntity;

public interface InfoRepository extends JpaRepository<MemberEntity, Long> {

	Long countByMemberId(String memberId);

	Long countByNickname(String nickname);

	Long countByEmailAndDeletionDateAndBanDate(String email, Date deletionDate, Date banDate);

}
