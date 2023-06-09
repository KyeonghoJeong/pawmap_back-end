package com.pawmap.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/member/reissuance")
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
	
	@GetMapping("/member")
	public ResponseEntity<?> getMember(HttpServletRequest request) {
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("Invalid");
		}else {
			MemberDto memberDto = memberService.getMember(SecurityContextHolder.getContext().getAuthentication().getName());
			
			return ResponseEntity.ok().body(memberDto);
		}
	}
	
	@PutMapping("/member")
	public ResponseEntity<?> putMember(HttpServletRequest request, @RequestBody SignInDto memberInfo){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("Invalid");
		}else {
			memberService.putMember(memberInfo);

			return ResponseEntity.ok("Success");
		}
	}
	
	@DeleteMapping("/member")
	public ResponseEntity<?> deleteMember(@RequestBody Map<String, String> memberInfo){
		String username = memberInfo.get("memberId");
		String password = memberInfo.get("pw");

		memberService.deleteMember(username, password);
		
		return ResponseEntity.ok().body("Success");
	}
	
}
