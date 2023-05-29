package com.pawmap.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawmap.member.entity.MemberEntity;

public interface InfoRepository extends JpaRepository<MemberEntity, Long> {

	Long countByMemberId(String memberId);

	Long countByNickname(String nickname);

	Long countByEmail(String email);

}
