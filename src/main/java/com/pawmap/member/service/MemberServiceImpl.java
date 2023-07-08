package com.pawmap.member.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawmap.member.dao.MemberDao;
import com.pawmap.member.dao.RefreshTokenDao;
import com.pawmap.member.dto.DetailedMemberDto;
import com.pawmap.member.dto.MemberDto;
import com.pawmap.member.dto.SignInDto;
import com.pawmap.member.entity.MemberEntity;
import com.pawmap.member.entity.RefreshTokenEntity;
import com.pawmap.security.JwtTokenProvider;

@Service
@Transactional
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
	
	@Autowired
	private ModelMapper modelMapper;

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
				"ROLE_MEMBER",
				null,
				null
		);
		
		memberDao.signUp(memberEntity);
	}

	@Override
	public String[] signIn(String username, String password) {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
		
		// CustomAuthenticationProvider 회원 유효성 검사
		Authentication authenticatedMember = authenticationManager.authenticate(authentication);
		
		Collection<? extends GrantedAuthority> authorities = authenticatedMember.getAuthorities();
		GrantedAuthority firstAuthority = authorities.iterator().next();
		String authority = firstAuthority.getAuthority();
		
		String info[] = new String[3];
		info[0] = jwtTokenProvider.generateAccessToken(authenticatedMember);
		info[1] = jwtTokenProvider.generateRefreshToken(authenticatedMember);
		info[2] = authority;
		
		return info;
	}

	// accessToken 재발급 서비스
	@Override
	public String getAccessToken(String refreshToken) {
		// TODO Auto-generated method stub
		// jwtTokenProvider에서 유효성 검사 후 토큰이 유효한 경우
		if(jwtTokenProvider.validateToken(refreshToken)) {
			// refreshToken 테이블에서 해당 refreshToken을 가지고 있는 row 가져오기
			RefreshTokenEntity refreshTokenEntity = refreshTokenDao.getRefreshToken(refreshToken);
			
			// 해당 refreshToken을 가지고 있는 회원의 아이디로 회원 검색 및 userDetails 객체 만들기
			String username = refreshTokenEntity.getMemberId();
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			// 인증된 회원 Authentication 객체 생성
			// 파라미터 각각 회원 엔티티, 비밀번호 (인증 이후 필요 없으므로 null), 회원 권한
			Authentication authenticatedMember = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
			String accessToken = jwtTokenProvider.generateAccessToken(authenticatedMember);
			
			return accessToken;
		}else {
			return "invalidRefreshToken";
		}
	}

	// 회원정보 리턴 서비스 메소드
	@Override
	public MemberDto getMember(String memberId) {
		// TODO Auto-generated method stub
		MemberEntity memberEntity = memberDao.getMember(memberId);
		
		MemberDto memberDto = modelMapper.map(memberEntity, MemberDto.class);
		memberDto.setPw(null);
		
		return memberDto;
	}

	@Override
	public void putMember(SignInDto memberInfo) {
		// TODO Auto-generated method stub
		memberInfo.setPw(passwordEncoder.encode(memberInfo.getPw()));
		
		memberDao.putMember(memberInfo);
	}

	@Override
	public void deleteMember(String username, String password) {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
		
		// CustomAuthenticationProvider 회원 유효성 검사
		Authentication authenticatedMember = authenticationManager.authenticate(authentication);
		
		Date deletionDate = Calendar.getInstance().getTime();
		
		memberDao.deleteMember(authenticatedMember.getName(), deletionDate);
	}

	@Override
	public Page<DetailedMemberDto> getMembers(String memberId, String nickname, String email, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<MemberEntity> memberEntities = memberDao.getMembers(memberId, nickname, email, pageable);
		
		Page<DetailedMemberDto> datailedMemberDtos = memberEntities.map(DetailedMemberDto::new);
		
		return datailedMemberDtos;
	}

	@Override
	public void updateBanDate(String memberId, String order) {
		// TODO Auto-generated method stub
		Date banDate = null;
		
		if(order.equals("ban")) {
			banDate = Calendar.getInstance().getTime();
		}
		
		memberDao.updateBanDate(memberId, banDate);
	}

}
