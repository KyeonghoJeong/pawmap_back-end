package com.pawmap.security;

import java.security.Key;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.pawmap.member.dao.RefreshTokenDao;
import com.pawmap.member.entity.RefreshTokenEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

// jwt 발급 클래스

@Slf4j
@Component
public class JwtTokenProvider {
	
	// refreshToken 발급 시 관련 정보를 DB에 저장하기 위한 인터페이스
	// accessToken 재발급 시 refreshToken 유효성 검사 하는 데 사용 => 오라클 스케줄러에 의해 1시간 마다 refreshToken 만기 시간이 지난 row 자동으로 삭제
	@Autowired
	private RefreshTokenDao refreshTokenDao;

	private final Key key; // jwt 서명 및 증명에 사용되는 키
	
	// 키 생성에 사용되는 시크릿 키 (String)
	// 키 생성 시 안전하고 유일한 키를 생성하기 위해 필요
	// 리눅스에서 openssl rand -base64 64로 512bits 길이의 base64로 인코딩한 키 생성
	private final String jwtSecretKey = "adJl+xrI5sp/bEzRFL5UUJ1zUoW15Oytr1xt1rVTRQGFM6FDvyocsFnkvDlY2mFzgoelJbn8zH4MgYvTFgTSeg==";
	
	public JwtTokenProvider() {
		byte[] byteKey = Decoders.BASE64.decode(jwtSecretKey); // jwtSecretKey 디코딩 후 byte형으로 변환
		this.key = Keys.hmacShaKeyFor(byteKey); // JWT 서명과 증명을 위한 키를 HMAC-SHA 알고리즘으로 생성
	}
	
	// accessToken 생성 메소드
	public String generateAccessToken(Authentication authenticatedMember) {
		// 인증된 회원(authenticatedMember)의 권한 리스트에 있는 각 권한을 가져와서 ","로 구분하여 매핑
		String authorities = authenticatedMember.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		
		// jwt 토큰 만들기 => 아이디, 권한, 유효기간, 키 설정
		String accessToken = Jwts.builder()
				.setSubject(authenticatedMember.getName())
				.claim("auth", authorities)
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1시간
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
	
		return accessToken;
	}
	
	public String generateRefreshToken(Authentication authenticatedMember) {
		String refreshToken = Jwts.builder()
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // 1주
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
		
		// refreshToken 엔티티 생성
		// 파라미터는 각각 토큰 id(시퀀스), 회원 아이디, refreshToken, 만기 시간
		RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity(
				null, 
				authenticatedMember.getName(), 
				refreshToken,
				new Timestamp(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7) // 1주
		);
		
		refreshTokenDao.addRefreshToken(refreshTokenEntity); // DB 테이븰에 추가
	
		return refreshToken;
	}

	// JwtAutheticationFilter에서 실행
	// 토큰 유효성 검사 메소드
	public boolean validateToken(String jwtToken) {
		// TODO Auto-generated method stub
		try {
			// jwt 토큰을 만들 때 사용한 서명 key로 jwt parser를 설정하고 파라미터로 받은 jwt 토큰 유효성 증명 => 성공 시 claim 리턴
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);

			return true;
		}catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			// 보안 문제 발생, 제대로 인코딩되지 않은 경우, 토큰의 구조가 부적절한 경우(header, payload, signature) 예외 처리
			log.info("Invalid JWT Token", e);
		}catch (ExpiredJwtException e) {
			// jwt 토큰 기간 만료 시 예외 처리
			log.info("Expired JWT Token", e);
		}catch (UnsupportedJwtException e) {
			// 지원되지 않거나 인정되지 않는 claim을 가지고 있거나 지원되지 않는 jwt 토큰 포맷인 경우 예외 처리
			log.info("Unsupported JWT Token", e);
		}catch (IllegalArgumentException e){
			// 키, 포맷, 클레임 등이 유효하지 않은 형식이나 값인 경우 예외 처리
			log.info("JWT claims string is empty.", e);
		}
		
		return false; // 유효하지 않은 경우 false 리턴
	}

	// JwtAutheticationFilter에서 validateToken 메소드 이후(유효성 검사 완료 후) 실행 
	// 인증 받은 회원 정보를 담은 authentication 객체 리턴
	public Authentication getAuthentication(String jwtToken) {
		// TODO Auto-generated method stub
		Claims claims  = parseClaims(jwtToken); // 클레임 받기
		
		// 클레임 중 auth라는 이름이 없는 경우 부적절한 토큰 => 예외 처리
		if(claims.get("auth") == null) {
			throw new RuntimeException("Not Authorized Token");
		}
		
		// auth라는 이름으로 들어있는 클레임들은 콤마로 구분되어 있고 이 콤마로 구분하여 각 권한 목록을 Collection 타입으로 매핑    
		Collection<? extends GrantedAuthority> authorities =
				Arrays.stream(claims.get("auth").toString().split(","))
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
		
		// UserDetails 인터페이스는 jwt 토큰 발급 시 회원 정보의 표준화, 통합, 보안, 확장, 변경(커스텀)을 가능하게 해주는 클래스
		// 회원 아이디, 비밀번호, 권한 목록으로 UserDetails 객체 생성
		UserDetails userDetails = new User(claims.getSubject(), "", authorities);
		
		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities); // 인증된 authentication 객체 리턴
	}

	// jwt 토큰에서 클레임 추출해서 리턴
	private Claims parseClaims(String jwtToken) {
		// TODO Auto-generated method stub
		try {
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
	
}
