package com.pawmap.member.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pawmap.member.entity.MemberEntity;
import com.pawmap.member.entity.RefreshTokenEntity;
import com.pawmap.member.repository.MemberRepository;
import com.pawmap.member.repository.RefreshTokenRepository;

@Repository
public class AuthDaoImpl implements AuthDao {
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public RefreshTokenEntity getRefreshToken(String refreshToken) {
		// TODO Auto-generated method stub
		RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findByRefreshToken(refreshToken); // refreshToken 값으로 조회
		
		return refreshTokenEntity;
	}
	
	@Override
	public void postMember(MemberEntity memberEntity) {
		// TODO Auto-generated method stub
		memberRepository.save(memberEntity);
	}
	
}