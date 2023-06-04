package com.pawmap.member.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pawmap.member.entity.RefreshTokenEntity;
import com.pawmap.member.repository.RefreshTokenRepository;

@Repository
public class RefreshTokenDaoImpl implements RefreshTokenDao {
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Override
	public void addRefreshToken(RefreshTokenEntity refreshTokenEntity) {
		// TODO Auto-generated method stub
		refreshTokenRepository.save(refreshTokenEntity);
	}

	@Override
	public RefreshTokenEntity getRefreshToken(String refreshToken) {
		// TODO Auto-generated method stub
		RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findByRefreshToken(refreshToken);
		
		return refreshTokenEntity;
	}

}
