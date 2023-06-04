package com.pawmap.member.security;

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

@Slf4j
@Component
public class JwtTokenProvider {
	
	@Autowired
	private RefreshTokenDao refreshTokenDao;

	private final Key key;
	private final String jwtSecret = "VlwEyVBsYt9V7zq57TejMnVUyzblYcfPQye08f7MGVA9XkHa";
	
	public JwtTokenProvider() {
		byte[] byteKey = Decoders.BASE64.decode(jwtSecret);
		this.key = Keys.hmacShaKeyFor(byteKey);
	}
	
	public String generateAccessToken(Authentication authenticatedMember) {
		String authorities = authenticatedMember.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		
		String accessToken = Jwts.builder()
				.setSubject(authenticatedMember.getName())
				.claim("auth", authorities)
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 30)) // 테스트를 위해 30초로 설정
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	
		return accessToken;
	}
	
	public String generateRefreshToken(Authentication authenticatedMember) {
		String refreshToken = Jwts.builder()
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 1)) // 테스트를 위해 1분으로 설정
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
		
		RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity(
				null, 
				authenticatedMember.getName(), 
				refreshToken,
				new Timestamp(System.currentTimeMillis() + 1000 * 60 * 1)
		);
		
		refreshTokenDao.addRefreshToken(refreshTokenEntity);
	
		return refreshToken;
	}

	// With JwtAutheticationFilter
	public boolean validateToken(String jwtToken) {
		// TODO Auto-generated method stub
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);

			return true;
		}catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.info("Invalid JWT Token", e);
		}catch (ExpiredJwtException e) {
			log.info("Expired JWT Token", e);
		}catch (UnsupportedJwtException e) {
			log.info("Unsupported JWT Token", e);
		}catch (IllegalArgumentException e){
			log.info("JWT claims string is empty.", e);
		}
		
		return false;
	}

	// With JwtAutheticationFilter
	public Authentication getAuthentication(String jwtToken) {
		// TODO Auto-generated method stub
		Claims claims  = parseClaims(jwtToken);
		
		if(claims.get("auth") == null) {
			throw new RuntimeException("Not Authorized Token");
		}
		
		Collection<? extends GrantedAuthority> authorities =
				Arrays.stream(claims.get("auth").toString().split(","))
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
		
		UserDetails userDetails = new User(claims.getSubject(), "", authorities);
		
		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	}

	private Claims parseClaims(String jwtToken) {
		// TODO Auto-generated method stub
		try {
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
	
}
