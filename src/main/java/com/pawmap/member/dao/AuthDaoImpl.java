package com.pawmap.member.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.pawmap.member.entity.MemberEntity;
import com.pawmap.member.entity.RefreshTokenEntity;
import com.pawmap.member.repository.AuthRepository;

public class AuthDaoImpl implements AuthDao {
	
	@Autowired
	private AuthRepository authRepository;
	
	@Override
	public RefreshTokenEntity getRefreshToken(String refreshToken) {
		// TODO Auto-generated method stub
		RefreshTokenEntity refreshTokenEntity = authRepository.findByRefreshToken(refreshToken); // refreshToken 값으로 조회
		
		return refreshTokenEntity;
	}
	
	@Override
	public void postMember(MemberEntity memberEntity) {
		// TODO Auto-generated method stub
		authRepository.save(memberEntity);
	}
}