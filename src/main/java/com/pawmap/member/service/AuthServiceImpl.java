package com.pawmap.member.service;

import java.util.Collection;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawmap.member.dao.AuthDao;
import com.pawmap.member.dto.AuthDto;
import com.pawmap.member.dto.MemberDto;
import com.pawmap.member.entity.MemberEntity;
import com.pawmap.member.entity.RefreshTokenEntity;
import com.pawmap.security.JwtTokenProvider;

@Service
@Transactional // 트랜잭션 설정
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JavaMailSender javaMailSender; // application.yml에 smtp 설정
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AuthDao authDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 이메일 인증코드 발송 및 리턴 메소드
	@Override
	public String getEmailAuthCode(String email) {
		// TODO Auto-generated method stub
		Random random = new Random(); // 난수 발생
		int number = random.nextInt(8889) + 1111; // nextInt(n) + a => n는 포함 X (0 ~ n-1), a는 기존 범위를 증가시킬 값 => 0~8888 => 1111~9999 사이의 난수 발생 
		String emailAuthCode = Integer.toString(number); // 형변환
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage(); // Mime 포맷 메시지 객체 생성

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // 보낼 메일 메시지 객체, multipart 여부, 인코딩 타입
			
			helper.setFrom("kyeonghojeong@naver.com"); // 발신자 => application.yml에 설정한 메일과 같은 메일
			helper.setTo(email); // 수신자 => 회원이 입력한 메일
			helper.setSubject("회원가입 인증 메일입니다."); // 메일 제목
			helper.setText("<h2>pawmap 회원가입을 위해 아래의 숫자를 입력해주세요.</h2><h1>"+emailAuthCode+"</h1>", true); // true는 html 여부
			
			javaMailSender.send(mimeMessage); // 인증 메일 발송
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return emailAuthCode; // 인증 메일 발송 후 인증코드 리턴
	}
	
	// 회원가입 서비스 메소드
	@Override
	public void postMember(MemberDto memberDto) {
		// TODO Auto-generated method stub
		String encodedPw = passwordEncoder.encode(memberDto.getPw()); // 비밀번호 암호화
		
		// 회원 엔티티 객체 생성
		MemberEntity memberEntity = new MemberEntity(
				memberDto.getMemberId(), // 입력받은 아이디
				encodedPw, // 암호화 한 입력 비밀번호
				memberDto.getNickname(), // 입력받은 닉네임
				memberDto.getEmail(), // 입력받은 이메일
				"ROLE_MEMBER", // 권한 => 회원
				null, // 탈퇴 날짜 null
				null // 차단 날짜 null
		);
		
		authDao.postMember(memberEntity);
	}
	
	// 로그인 서비스 메소드
	@Override
	public AuthDto signIn(MemberDto memberDto) {
		// TODO Auto-generated method stub
		String memberId = memberDto.getMemberId(); // 입력받은 회원 아이디 가져오기
		String pw = memberDto.getPw(); // 입력받은 비밀번호 가져오기
		
		// authentication 인증 객체 생성
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(memberId, pw);
		
		// CustomAuthenticationProvider 회원 유효성 검사
		Authentication authenticatedMember = authenticationManager.authenticate(authentication); // 유효하지 않을 경우 예외발생
		
		String accessToken = jwtTokenProvider.generateAccessToken(authenticatedMember); // accessToken 생성
		String refreshToken = jwtTokenProvider.generateRefreshToken(authenticatedMember); // refreshToken 생성
		
		// authentication 객체에서 권한 가져와서 권한 하나 추출 => 각 회원은 하나의 권한만 갖음 
		Collection<? extends GrantedAuthority> authorities = authenticatedMember.getAuthorities();
		GrantedAuthority authority = authorities.iterator().next();
		String role = authority.getAuthority();
		
		AuthDto authDto = new AuthDto(role, accessToken, refreshToken); // AuthDto 객체 생성

		return authDto;
	}
	
	// accessToken 재발급 서비스
	@Override
	public String getAccessToken(String refreshToken) {
		// TODO Auto-generated method stub
		// jwtTokenProvider에서 유효성 검사 후 토큰이 유효한 경우
		if(jwtTokenProvider.validateToken(refreshToken)) {
			// refreshToken 테이블에서 해당 refreshToken을 가지고 있는 엔티티(row) 가져오기
			// refreshToken 엔티티는 refreshToken id, 회원 id, refreshToken, 만기 시간 속성을 가지고 있으며 1시간 마다 스케줄러에 의해 만기 시간이 지난 row는 삭제됨
			RefreshTokenEntity refreshTokenEntity = authDao.getRefreshToken(refreshToken);
			
			String memberId = refreshTokenEntity.getMemberId(); // 해당 refreshToken을 가지고 있는 회원의 아이디 가져오기
			UserDetails userDetails = userDetailsService.loadUserByUsername(memberId); // 회원 아이디로 회원 검색 및 userDetails 객체 만들기
			
			// 인증된 회원 Authentication 객체 생성
			// 파라미터 각각 회원 엔티티, 비밀번호 (인증 이후 필요 없으므로 null), 회원 권한
			Authentication authenticatedMember = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
			String accessToken = jwtTokenProvider.generateAccessToken(authenticatedMember); // accessToken 재발급
			
			return accessToken; // accessToken 리턴
		}else {
			// 토큰이 유효하지 않으면 null 리턴
			return null;
		}
	}

}
