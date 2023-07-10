package com.pawmap.member.dao;

import com.pawmap.member.entity.MemberEntity;
import com.pawmap.member.entity.RefreshTokenEntity;

public interface AuthDao {

	RefreshTokenEntity getRefreshToken(String refreshToken);
	
	void postMember(MemberEntity memberEntity);

}
