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

	// ȸ�� ���̵�� ȸ������ ��ȸ
	// Ż�� ��¥, ���� ��¥ �� �� null => ���� �̿밡�� ȸ��
	Optional<MemberEntity> findByMemberIdAndDeletionDateAndBanDate(String memberId, Date deletionDate, Date banDate);
	
	// ȸ�� ���� ����
	
	// @Modifying�� nativeQuery���� ����, ���� �� �ݵ�� �ʿ��� ������̼�
	// ȸ�� ���̵�� ��ȸ�Ͽ� �ش� row�� ��й�ȣ �� ����
	@Modifying
	@Query(value="UPDATE memberInfo SET pw = :pw WHERE memberId = :memberId", nativeQuery=true)
	void putMemberPw(@Param("memberId") String memberId, @Param("pw") String pw);
	
	// @Modifying�� nativeQuery���� ����, ���� �� �ݵ�� �ʿ��� ������̼�
	// deletionDate Į�� ���� ��¥ �����Ͱ� ������ Ż�� ����, null�̸� �̿밡�� ȸ�� => �ش� ȸ�� ���̵�� ��ȸ�Ͽ� Ż��¥ Į���� �� ����
	@Modifying
	@Query(value="UPDATE memberInfo SET deletionDate = :deletiondate WHERE memberId = :memberid", nativeQuery=true)
	void putMemberDeletionDate(@Param("memberid") String memberId, @Param("deletiondate") Date deletionDate);
	
	// @Modifying�� nativeQuery���� ����, ���� �� �ݵ�� �ʿ��� ������̼�
	// banDate Į�� ���� ��¥ �����Ͱ� ������ ���� ����, null�̸� �̿밡�� ȸ�� => �ش� ȸ�� ���̵�� ��ȸ�Ͽ� ���ܳ�¥ Į���� �� ����
	@Modifying
	@Query(value="UPDATE memberInfo SET banDate = :banDate WHERE memberId = :memberId", nativeQuery=true)
	void putMemberBanDate(@Param("memberId") String memberId, @Param("banDate") Date banDate);
	
	// ��ü ȸ�� ��ȸ �� ���� �˻�

	// deletionDate ���� null�� ���� Ż�� ȸ������ ��ȸ �� �����ؾ� �ϹǷ� null�� row ��ȸ
	Page<MemberEntity> findByDeletionDate(Date deletionDate, Pageable pageable);

	// LIKE�� �Ἥ ȸ�� ���̵� �˻�� ���ԵǴ� ��� ��� ��ȸ
	// deletionDate ���� null�� ���� Ż�� ȸ������ ��ȸ �� �����ؾ� �ϹǷ� null�� row ��ȸ
	@Query(value="SELECT * FROM memberinfo WHERE memberId LIKE %:memberId% AND deletionDate IS NULL", nativeQuery=true)
	Page<MemberEntity> getMembersByMemberId(@Param("memberId") String memberId, Pageable pageable);

	// LIKE�� �Ἥ �г��ӿ� �˻�� ���ԵǴ� ��� ��� ��ȸ
	// deletionDate ���� null�� ���� Ż�� ȸ������ ��ȸ �� �����ؾ� �ϹǷ� null�� row ��ȸ
	@Query(value="SELECT * FROM memberinfo WHERE nickname LIKE %:nickname% AND deletionDate IS NULL", nativeQuery=true)
	Page<MemberEntity> getMembersByNickname(@Param("nickname") String nickname, Pageable pageable);

	// LIKE�� �Ἥ �̸��Ͽ� �˻�� ���ԵǴ� ��� ��� ��ȸ
	// deletionDate ���� null�� ���� Ż�� ȸ������ ��ȸ �� �����ؾ� �ϹǷ� null�� row ��ȸ
	@Query(value="SELECT * FROM memberinfo WHERE email LIKE %:email% AND deletionDate IS NULL", nativeQuery=true)
	Page<MemberEntity> getMembersByEmail(@Param("email") String email, Pageable pageable);
	
	// ���̵�, �г���, �̸��� �����ϴ��� count
	
	// ȸ�� ���̵�� ��ȸ�Ͽ� ����
	Long countByMemberId(String memberId);

	// �г������� ��ȸ�Ͽ� ī��Ʈ ����
	Long countByNickname(String nickname);
	
	// ���� �ּ�, ���� ��¥�� ��ȸ�Ͽ� ī��Ʈ ���� => ���� �̸����� ��� �Ұ�
	@Query(value="SELECT COUNT(*) FROM memberInfo WHERE email = :email AND banDate IS NOT NULL", nativeQuery=true)
	Long getEmailNumber(@Param("email") String email);

}
