package com.pawmap.member.dao;

import com.pawmap.member.entity.RefreshTokenEntity;

public interface RefreshTokenDao {

	void addRefreshToken(RefreshTokenEntity refreshTokenEntity);

	RefreshTokenEntity getRefreshToken(String refreshToken);

}
