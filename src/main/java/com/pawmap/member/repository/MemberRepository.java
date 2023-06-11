package com.pawmap.member.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pawmap.member.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long>{

	Optional<MemberEntity> findByMemberIdAndDeletionDateAndBanDate(String username, Date deletionDate, Date banDate);

	@Modifying
	@Query(value="UPDATE memberInfo SET pw = :pw WHERE memberId = :memberId", nativeQuery=true)
	void putMember(@Param("memberId") String memberId, @Param("pw") String pw);

	@Modifying
	@Query(value="UPDATE memberInfo SET deletionDate = :deletiondate WHERE memberId = :memberid", nativeQuery=true)
	void deleteByMember(@Param("memberid") String memberId, @Param("deletiondate") Date deletionDate);

	@Modifying
	@Query(value="UPDATE memberInfo SET banDate = :banDate WHERE memberId = :memberId", nativeQuery=true)
	void updateBanDate(@Param("memberId") String memberId, @Param("banDate") Date banDate);

	Page<MemberEntity> findByDeletionDate(Date deletionDate, Pageable pageable);

	@Query(value="SELECT * FROM memberinfo WHERE memberId LIKE %:memberId% AND deletionDate IS NULL", nativeQuery=true)
	Page<MemberEntity> getMembersByMemberId(@Param("memberId") String memberId, Pageable pageable);

	@Query(value="SELECT * FROM memberinfo WHERE nickname LIKE %:nickname% AND deletionDate IS NULL", nativeQuery=true)
	Page<MemberEntity> getMembersByNickname(@Param("nickname") String nickname, Pageable pageable);

	@Query(value="SELECT * FROM memberinfo WHERE email LIKE %:email% AND deletionDate IS NULL", nativeQuery=true)
	Page<MemberEntity> getMembersByEmail(@Param("email") String email, Pageable pageable);

}
