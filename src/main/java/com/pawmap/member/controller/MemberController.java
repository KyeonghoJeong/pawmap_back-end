package com.pawmap.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.member.dto.MemberDto;
import com.pawmap.member.dto.SignInDto;
import com.pawmap.member.service.MemberService;

@RestController
@RequestMapping("api/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody MemberDto memberInfo) {
		memberService.signUp(memberInfo);
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto, HttpServletResponse response) {
		String username = signInDto.getMemberId();
		String password = signInDto.getPw();
		
		String[] tokens = memberService.signIn(username, password);
		
		String accessToken = tokens[0];
		String refreshToken = tokens[1];
		
		ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
				.maxAge(7 * 24 * 60 * 60)
				.path("/")
				.secure(true)
				.sameSite("None")
				.httpOnly(true)
				.build();

		response.setHeader("Set-cookie", cookie.toString());
		
		Map<String, String> responseBody = new HashMap<>();
		responseBody.put("accessToken", accessToken);
		
		return ResponseEntity.ok(responseBody);
	}
	
	@PostMapping("/reissuance")
	public ResponseEntity<?> getAccessToken(@CookieValue("refreshToken") String refreshToken){
		String accessToken;
		
		accessToken = memberService.getAccessToken(refreshToken);
		
		if(accessToken == null) {
			return ResponseEntity.ok().body("Invalid");
		}else {
			Map<String, String> responseBody = new HashMap<>();
			responseBody.put("accessToken", accessToken);
			
			return ResponseEntity.ok(responseBody);
		}
	}
	
}
