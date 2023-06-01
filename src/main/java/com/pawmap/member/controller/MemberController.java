package com.pawmap.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.member.dto.JwtTokenDto;
import com.pawmap.member.dto.MemberDto;
import com.pawmap.member.dto.SignInDto;
import com.pawmap.member.service.MemberService;

@RestController
@RequestMapping("api")
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@PostMapping("/member/signup")
	public ResponseEntity<?> signUp(@RequestBody MemberDto memberInfo) {
		memberService.signUp(memberInfo);
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/member/signin")
	public JwtTokenDto signIn(@RequestBody SignInDto signInDto) {
		String username = signInDto.getMemberId();
		String password = signInDto.getPw();
		
		JwtTokenDto jwtTokenDto = memberService.signIn(username, password);
		
		return jwtTokenDto;
	}
	
}
