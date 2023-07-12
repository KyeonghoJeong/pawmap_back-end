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

// jwt �߱� Ŭ����

@Slf4j
@Component
public class JwtTokenProvider {
	
	// refreshToken �߱� �� ���� ������ DB�� �����ϱ� ���� �������̽�
	// accessToken ��߱� �� refreshToken ��ȿ�� �˻� �ϴ� �� ��� => ����Ŭ �����ٷ��� ���� 1�ð� ���� refreshToken ���� �ð��� ���� row �ڵ����� ����
	@Autowired
	private RefreshTokenDao refreshTokenDao;

	private final Key key; // jwt ���� �� ���� ���Ǵ� Ű
	
	// Ű ������ ���Ǵ� ��ũ�� Ű (String)
	// Ű ���� �� �����ϰ� ������ Ű�� �����ϱ� ���� �ʿ�
	// ���������� openssl rand -base64 64�� 512bits ������ base64�� ���ڵ��� Ű ����
	private final String jwtSecretKey = "adJl+xrI5sp/bEzRFL5UUJ1zUoW15Oytr1xt1rVTRQGFM6FDvyocsFnkvDlY2mFzgoelJbn8zH4MgYvTFgTSeg==";
	
	public JwtTokenProvider() {
		byte[] byteKey = Decoders.BASE64.decode(jwtSecretKey); // jwtSecretKey ���ڵ� �� byte������ ��ȯ
		this.key = Keys.hmacShaKeyFor(byteKey); // JWT ����� ������ ���� Ű�� HMAC-SHA �˰������� ����
	}
	
	// accessToken ���� �޼ҵ�
	public String generateAccessToken(Authentication authenticatedMember) {
		// ������ ȸ��(authenticatedMember)�� ���� ����Ʈ�� �ִ� �� ������ �����ͼ� ","�� �����Ͽ� ����
		String authorities = authenticatedMember.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		
		// jwt ��ū ����� => ���̵�, ����, ��ȿ�Ⱓ, Ű ����
		String accessToken = Jwts.builder()
				.setSubject(authenticatedMember.getName())
				.claim("auth", authorities)
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1�ð�
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
	
		return accessToken;
	}
	
	public String generateRefreshToken(Authentication authenticatedMember) {
		String refreshToken = Jwts.builder()
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // 1��
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
		
		// refreshToken ��ƼƼ ����
		// �Ķ���ʹ� ���� ��ū id(������), ȸ�� ���̵�, refreshToken, ���� �ð�
		RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity(
				null, 
				authenticatedMember.getName(), 
				refreshToken,
				new Timestamp(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7) // 1��
		);
		
		refreshTokenDao.addRefreshToken(refreshTokenEntity); // DB ���̕��� �߰�
	
		return refreshToken;
	}

	// JwtAutheticationFilter���� ����
	// ��ū ��ȿ�� �˻� �޼ҵ�
	public boolean validateToken(String jwtToken) {
		// TODO Auto-generated method stub
		try {
			// jwt ��ū�� ���� �� ����� ���� key�� jwt parser�� �����ϰ� �Ķ���ͷ� ���� jwt ��ū ��ȿ�� ���� => ���� �� claim ����
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);

			return true;
		}catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			// ���� ���� �߻�, ����� ���ڵ����� ���� ���, ��ū�� ������ �������� ���(header, payload, signature) ���� ó��
			log.info("Invalid JWT Token", e);
		}catch (ExpiredJwtException e) {
			// jwt ��ū �Ⱓ ���� �� ���� ó��
			log.info("Expired JWT Token", e);
		}catch (UnsupportedJwtException e) {
			// �������� �ʰų� �������� �ʴ� claim�� ������ �ְų� �������� �ʴ� jwt ��ū ������ ��� ���� ó��
			log.info("Unsupported JWT Token", e);
		}catch (IllegalArgumentException e){
			// Ű, ����, Ŭ���� ���� ��ȿ���� ���� �����̳� ���� ��� ���� ó��
			log.info("JWT claims string is empty.", e);
		}
		
		return false; // ��ȿ���� ���� ��� false ����
	}

	// JwtAutheticationFilter���� validateToken �޼ҵ� ����(��ȿ�� �˻� �Ϸ� ��) ���� 
	// ���� ���� ȸ�� ������ ���� authentication ��ü ����
	public Authentication getAuthentication(String jwtToken) {
		// TODO Auto-generated method stub
		Claims claims  = parseClaims(jwtToken); // Ŭ���� �ޱ�
		
		// Ŭ���� �� auth��� �̸��� ���� ��� �������� ��ū => ���� ó��
		if(claims.get("auth") == null) {
			throw new RuntimeException("Not Authorized Token");
		}
		
		// auth��� �̸����� ����ִ� Ŭ���ӵ��� �޸��� ���еǾ� �ְ� �� �޸��� �����Ͽ� �� ���� ����� Collection Ÿ������ ����    
		Collection<? extends GrantedAuthority> authorities =
				Arrays.stream(claims.get("auth").toString().split(","))
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
		
		// UserDetails �������̽��� jwt ��ū �߱� �� ȸ�� ������ ǥ��ȭ, ����, ����, Ȯ��, ����(Ŀ����)�� �����ϰ� ���ִ� Ŭ����
		// ȸ�� ���̵�, ��й�ȣ, ���� ������� UserDetails ��ü ����
		UserDetails userDetails = new User(claims.getSubject(), "", authorities);
		
		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities); // ������ authentication ��ü ����
	}

	// jwt ��ū���� Ŭ���� �����ؼ� ����
	private Claims parseClaims(String jwtToken) {
		// TODO Auto-generated method stub
		try {
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
	
}
