package com.pawmap.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawmap.member.entity.MemberEntity;
import com.pawmap.member.entity.RefreshTokenEntity;

public interface AuthRepository extends JpaRepository<RefreshTokenEntity, Long>{
	
	RefreshTokenEntity findByRefreshToken(String refreshToken); // refreshToken ������ ��ȸ

	void save(MemberEntity memberEntity);

}
