package com.pawmap.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pawmap.member.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long>{

	Optional<MemberEntity> findByMemberId(String username);

	@Modifying
	@Query(value="UPDATE memberInfo SET pw = :pw WHERE memberId = :memberId", nativeQuery=true)
	void putMember(@Param("memberId") String memberId, @Param("pw") String pw);

}
