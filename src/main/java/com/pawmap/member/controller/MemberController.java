package com.pawmap.member.controller;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.member.dto.DetailedMemberDto;
import com.pawmap.member.dto.MemberDto;
import com.pawmap.member.dto.SignInDto;
import com.pawmap.member.service.MemberService;

// 회원(member)과 관련한 컨트롤러
// 회원가입, 로그인, 회원탈퇴
// accessToken 재발급, 회원권한 조회
// 회원정보 조회, 회원정보 수정
// 회원목록 조회, 회원차단 

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
	
	// 로그인 메소드
	@PostMapping("/member/signin")
	public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto, HttpServletResponse response) {
		String username = signInDto.getMemberId();
		String password = signInDto.getPw();
		
		String[] tokens = memberService.signIn(username, password);
		
		String accessToken = tokens[0];
		String refreshToken = tokens[1];
		
		// 쿠키 생성
		ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
				.maxAge(Duration.ofHours(2)) // 쿠키 시간 설정 => refreshToken 만기 시간과 동일하게 설정
				.path("/") // 프론트엔드 측에서 쿠키를 허용할 path 설정
				//.secure(true) // https 연결로 보내기
				.sameSite("None") // 다른 도메인으로 보내는 것 허용
				.httpOnly(true) // http 또는 https만 접근 허용하여 자바스크립트에서 쿠키에 접근하여 수정하는 것 방지
				.build();

		response.setHeader("Set-cookie", cookie.toString());
		
		Map<String, String> responseBody = new HashMap<>();
		responseBody.put("accessToken", accessToken);
		
		return ResponseEntity.ok(responseBody);
	}
	
	// accessToken 재발급 메소드
	// refreshToken을 Cookie로 받음 (보안을 위해 refreshToken은 Cookie에 담아 송수신 <=> path, secure, sameStie, httpOnly)
	@GetMapping("/member/accesstoken")
	public ResponseEntity<?> getAccessToken(@CookieValue("refreshToken") String refreshToken){
		String accessToken;
		
		accessToken = memberService.getAccessToken(refreshToken);
		
		if(accessToken.equals("invalidRefreshToken")) {
			return ResponseEntity.ok().body("invalidRefreshToken");
		}else {
			Map<String, String> responseBody = new HashMap<>();
			responseBody.put("accessToken", accessToken);
			
			return ResponseEntity.ok().body(responseBody);
		}
	}
	
	// 회원권한 조회 (로그인 되어 있는 경우, 회원 또는 관리자의 요청)
	@GetMapping("/member/authority")
	public String getAuthLevel(HttpServletRequest request) {
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return "invalidToken";
		}else {
			Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			
			GrantedAuthority firstAuthority = authorities.iterator().next();
			String authority = firstAuthority.getAuthority();
			
			return authority;
		}
	}
	
	// 특정 회원의 게시글만 조회하기 위해 회원 아이디를 수신하기 위한 메소드
	@GetMapping("/member")
	public ResponseEntity<?> getMember(HttpServletRequest request) {
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("invalidToken");
		}else {
			MemberDto memberDto = memberService.getMember(SecurityContextHolder.getContext().getAuthentication().getName());
			
			return ResponseEntity.ok().body(memberDto);
		}
	}
	
	@PutMapping("/member")
	public ResponseEntity<?> putMember(HttpServletRequest request, @RequestBody SignInDto memberInfo){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("invalidToken");
		}else {
			memberService.putMember(memberInfo);

			return ResponseEntity.ok("Success");
		}
	}
	
	@PutMapping("/member/deletion")
	public ResponseEntity<?> deleteMember(@RequestBody Map<String, String> memberInfo){
		String username = memberInfo.get("memberId");
		String password = memberInfo.get("pw");
		
		memberService.deleteMember(username, password);
		
		return ResponseEntity.ok().body("Success");
	}
	
	@GetMapping("/members")
	public ResponseEntity<?> getMembers(
			HttpServletRequest request, 
			@RequestParam("memberId") String memberId,
			@RequestParam("nickname") String nickname,
			@RequestParam("email") String email,
			Pageable pageable){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("invalidToken");
		}else {
			Page<DetailedMemberDto> detailedMemberDtos = memberService.getMembers(memberId, nickname, email, pageable);

			return ResponseEntity.ok().body(detailedMemberDtos);
		}
	}
	
	@PutMapping("/member/ban")
	public ResponseEntity<?> updateBanDate(HttpServletRequest request, @RequestBody Map<String, String> orders){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("invalidToken");
		}else {
			String memberId = orders.get("memberId");
			String order = orders.get("order");
			
			memberService.updateBanDate(memberId, order);
			
			return ResponseEntity.ok().body("Success");
		}
	}
	
}
