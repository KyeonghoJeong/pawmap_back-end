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

	// 회원 아이디로 회원정보 조회
	// 탈퇴 날짜, 차단 날짜 둘 다 null => 현재 이용가능 회원
	Optional<MemberEntity> findByMemberIdAndDeletionDateAndBanDate(String memberId, Date deletionDate, Date banDate);
	
	// 회원 정보 수정
	
	// @Modifying은 nativeQuery에서 수정, 삭제 시 반드시 필요한 어노테이션
	// 회원 아이디로 조회하여 해당 row의 비밀번호 값 변경
	@Modifying
	@Query(value="UPDATE memberInfo SET pw = :pw WHERE memberId = :memberId", nativeQuery=true)
	void putMemberPw(@Param("memberId") String memberId, @Param("pw") String pw);
	
	// @Modifying은 nativeQuery에서 수정, 삭제 시 반드시 필요한 어노테이션
	// deletionDate 칼럼 값에 날짜 데이터가 있으면 탈퇴 상태, null이면 이용가능 회원 => 해당 회원 아이디로 조회하여 탈퇴날짜 칼럼의 값 수정
	@Modifying
	@Query(value="UPDATE memberInfo SET deletionDate = :deletiondate WHERE memberId = :memberid", nativeQuery=true)
	void putMemberDeletionDate(@Param("memberid") String memberId, @Param("deletiondate") Date deletionDate);
	
	// @Modifying은 nativeQuery에서 수정, 삭제 시 반드시 필요한 어노테이션
	// banDate 칼럼 값에 날짜 데이터가 있으면 차단 상태, null이면 이용가능 회원 => 해당 회원 아이디로 조회하여 차단날짜 칼럼의 값 수정
	@Modifying
	@Query(value="UPDATE memberInfo SET banDate = :banDate WHERE memberId = :memberId", nativeQuery=true)
	void putMemberBanDate(@Param("memberId") String memberId, @Param("banDate") Date banDate);
	
	// 전체 회원 조회 및 조건 검색

	// deletionDate 값이 null인 경우는 탈퇴 회원으로 조회 시 제외해야 하므로 null인 row 조회
	Page<MemberEntity> findByDeletionDate(Date deletionDate, Pageable pageable);

	// LIKE를 써서 회원 아이디에 검색어가 포함되는 경우 모두 조회
	// deletionDate 값이 null인 경우는 탈퇴 회원으로 조회 시 제외해야 하므로 null인 row 조회
	@Query(value="SELECT * FROM memberinfo WHERE memberId LIKE %:memberId% AND deletionDate IS NULL", nativeQuery=true)
	Page<MemberEntity> getMembersByMemberId(@Param("memberId") String memberId, Pageable pageable);

	// LIKE를 써서 닉네임에 검색어가 포함되는 경우 모두 조회
	// deletionDate 값이 null인 경우는 탈퇴 회원으로 조회 시 제외해야 하므로 null인 row 조회
	@Query(value="SELECT * FROM memberinfo WHERE nickname LIKE %:nickname% AND deletionDate IS NULL", nativeQuery=true)
	Page<MemberEntity> getMembersByNickname(@Param("nickname") String nickname, Pageable pageable);

	// LIKE를 써서 이메일에 검색어가 포함되는 경우 모두 조회
	// deletionDate 값이 null인 경우는 탈퇴 회원으로 조회 시 제외해야 하므로 null인 row 조회
	@Query(value="SELECT * FROM memberinfo WHERE email LIKE %:email% AND deletionDate IS NULL", nativeQuery=true)
	Page<MemberEntity> getMembersByEmail(@Param("email") String email, Pageable pageable);
	
	// 아이디, 닉네임, 이메일 존재하는지 count
	
	// 회원 아이디로 조회하여 리턴
	Long countByMemberId(String memberId);

	// 닉네임으로 조회하여 카운트 리턴
	Long countByNickname(String nickname);
	
	// 메일 주소, 차단 날짜로 조회하여 카운트 리턴 => 차단 이메일은 사용 불가
	@Query(value="SELECT COUNT(*) FROM memberInfo WHERE email = :email AND banDate IS NOT NULL", nativeQuery=true)
	Long getEmailNumber(@Param("email") String email);

}
