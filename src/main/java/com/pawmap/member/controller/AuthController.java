package com.pawmap.member.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.member.dto.AuthDto;
import com.pawmap.member.dto.MemberDto;
import com.pawmap.member.service.AuthService;

// accessToken 재발급, 이메일 인증 코드 발송 및 리턴, 로그인, 회원가입 등 회원 인증과 관련한 컨트롤러

@RestController
@RequestMapping("api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	// 회원가입 시 회원 이메일로 이메일 인증 코드를 발송하고 인증코드 검사를 위해 프론트엔드로 인증코드를 리턴하는 메소드
	@GetMapping("/email-auth-code")
	public ResponseEntity<?> getEmailAuthCode(@RequestParam String email) {
		String emailAuthCode = authService.getEmailAuthCode(email); // 인증 메일 발송 후 인증코드 가져오기

		return ResponseEntity.ok().body(emailAuthCode); // 인증코드 리턴
	}
	
	// 회원가입 메소드
	@PostMapping("/member")
	public ResponseEntity<?> postMember(@RequestBody MemberDto memberDto) {
		authService.postMember(memberDto); // 회원가입 서비스 메소드 호출
		
		return ResponseEntity.ok().build(); // 응답 코드 200(OK) 리턴
	}

	// 로그인 메소드
	@PostMapping("/sign-in")
	public ResponseEntity<?> signIn(@RequestBody MemberDto memberDto, HttpServletResponse response) {
		AuthDto authDto = authService.signIn(memberDto); // memberDto로 로그인 서비스 메소드 호출
		
		// 쿠키 생성
		// data에 들어있는 refreshToken 쿠키에 담기
		ResponseCookie cookie = ResponseCookie.from("refreshToken", authDto.getRefreshToken())
				//.maxAge(Duration.ofMinutes(1)) // 백엔드 리소스 요청 시 accessToken 및 refreshToken의 유효성을 확인하므로 cookie 만료 시간까지는 설정하지 않음
				.path("/") // 쿠키를 허용할 프론트엔드 측 path 설정 => "/"로 설정 시 모든 path 허용
				.secure(true) // https 연결로 보내기 => 보안 강화
				.sameSite("none") // none으로 설정 시 쿠키를 다른 도메인으로 보내는 것 허용
				.httpOnly(true) // true 설정 시 쿠키에 대해 http 요청 또는 https 요청만 접근을 허용하여 자바스크립트에서 쿠키에 접근하여 수정하는 것 방지
				.build();

		response.setHeader("Set-cookie", cookie.toString()); // HTTP 응답으로 쿠키를 설정할 때 응답 헤더 이름은 반드시 Set-Cookie로 설정 => 응답 헤더 Set-Cookie는 쿠키를 String으로 나타냄 => 따라서 cookie.toString()으로 설정
		
		authDto.setRefreshToken(null); // authDto에 있는 refreshToken 제거
		
		return ResponseEntity.ok(authDto); // refreshToken을 제외(헤더에 있으므로)한 authority, accessToken 리턴
	}
	
	// accessToken 재발급 메소드
	// refreshToken을 Cookie로 받음 (보안을 위해 refreshToken은 Cookie에 담아 송수신 <=> path, secure, sameStie, httpOnly)
	@GetMapping("/access-token")
	public ResponseEntity<?> getAccessToken(@CookieValue("refreshToken") String refreshToken){
		String accessToken = authService.getAccessToken(refreshToken); // 수신한 refreshToken으로 accessToken 재발급 시도
		
		if(accessToken == null) {
			// refreshToken이 유효하지 않아서 accessToken 재발급 실패한 경우 403 에러 코드 리턴
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}else {
			// authDto 객체에 accessToken 담아서 리턴
			AuthDto authDto = new AuthDto();
			authDto.setAccessToken(accessToken);

			return ResponseEntity.ok().body(authDto);
		}	
	}

}
