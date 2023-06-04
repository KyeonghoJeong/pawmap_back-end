package com.pawmap.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pawmap.member.dao.MemberDao;
import com.pawmap.member.dao.RefreshTokenDao;
import com.pawmap.member.dto.MemberDto;
import com.pawmap.member.entity.MemberEntity;
import com.pawmap.member.entity.RefreshTokenEntity;
import com.pawmap.member.security.JwtTokenProvider;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private RefreshTokenDao refreshTokenDao;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void signUp(MemberDto memberInfo) {
		// TODO Auto-generated method stub
		MemberDto memberDto = new MemberDto();
		
		memberDto.setMemberId(memberInfo.getMemberId());
		memberDto.setPw(passwordEncoder.encode(memberInfo.getPw()));
		memberDto.setNickname(memberInfo.getNickname());
		memberDto.setEmail(memberInfo.getEmail());
		
		MemberEntity memberEntity = new MemberEntity(
				memberDto.getMemberId(),
				memberDto.getPw(),
				memberDto.getNickname(),
				memberDto.getEmail(),
				"ROLE_USER"
		);
		
		memberDao.signUp(memberEntity);
	}

	@Override
	public String[] signIn(String username, String password) {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
		
		// CustomAuthenticationProvider 회원 유효성 검사
		Authentication authenticatedMember = authenticationManager.authenticate(authentication);
		
		String tokens[] = new String[2];
		tokens[0] = jwtTokenProvider.generateAccessToken(authenticatedMember);
		tokens[1] = jwtTokenProvider.generateRefreshToken(authenticatedMember);
		
		return tokens;
	}

	@Override
	public String getAccessToken(String refreshToken) {
		// TODO Auto-generated method stub
		if(jwtTokenProvider.validateToken(refreshToken)) {
			RefreshTokenEntity refreshTokenEntity = refreshTokenDao.getRefreshToken(refreshToken);
			
			String username = refreshTokenEntity.getMemberId();
		
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			Authentication authenticatedMember = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
			String accessToken = jwtTokenProvider.generateAccessToken(authenticatedMember);
			
			return accessToken;
		}else {
			System.out.println("유효하지 않습니다.");
			
			return null;
		}
	}

}
