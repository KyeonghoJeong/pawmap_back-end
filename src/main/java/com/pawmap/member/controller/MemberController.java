package com.pawmap.member.controller;

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

// ȸ��(member)�� ������ ��Ʈ�ѷ�
// ȸ������, �α���, ȸ��Ż��
// accessToken ��߱�, ȸ������ ��ȸ
// ȸ������ ��ȸ, ȸ������ ����
// ȸ����� ��ȸ, ȸ������ 

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
	
	// �α��� �޼ҵ�
	@PostMapping("/member/signin")
	public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto, HttpServletResponse response) {
		String username = signInDto.getMemberId();
		String password = signInDto.getPw();
		
		String[] info = memberService.signIn(username, password);
		
		String accessToken = info[0];
		String refreshToken = info[1];
		String authority = info[2];
		
		// ��Ű ����
		ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
				//.maxAge(Duration.ofMinutes(1)) // �鿣�� ���ҽ� ��û �� accessToken �� refreshToken�� ��ȿ���� Ȯ���ϹǷ� cookie ���� �ð������� �������� ����
				.path("/") // ����Ʈ���� ������ ��Ű�� ����� path ����
				.secure(true) // https ����� ������
				.sameSite("none") // �ٸ� ���������� ������ �� ���
				.httpOnly(true) // http �Ǵ� https�� ���� ����Ͽ� �ڹٽ�ũ��Ʈ���� ��Ű�� �����Ͽ� �����ϴ� �� ����
				.build();

		response.setHeader("Set-cookie", cookie.toString());
		
		Map<String, String> responseBody = new HashMap<>();
		responseBody.put("accessToken", accessToken);
		responseBody.put("authority", authority);
		
		return ResponseEntity.ok(responseBody);
	}
	
	// accessToken ��߱� �޼ҵ�
	// refreshToken�� Cookie�� ���� (������ ���� refreshToken�� Cookie�� ��� �ۼ��� <=> path, secure, sameStie, httpOnly)
	@GetMapping("/member/accesstoken")
	public ResponseEntity<?> getAccessToken(@CookieValue("refreshToken") String refreshToken){
		String accessToken = memberService.getAccessToken(refreshToken);
		
		if(accessToken.equals("invalidRefreshToken")) {
			return ResponseEntity.ok().body("invalidRefreshToken");
		}else {
			Map<String, String> responseBody = new HashMap<>();
			responseBody.put("accessToken", accessToken);
			
			return ResponseEntity.ok().body(responseBody);
		}	
	}
	
	// ȸ������ ��ȸ (�α��� �Ǿ� �ִ� ���, ȸ�� �Ǵ� �������� ��û)
	@GetMapping("/member/authority")
	public String getAuthLevel(HttpServletRequest request) {
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return "invalidAccessToken";
		}else {
			Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			
			GrantedAuthority firstAuthority = authorities.iterator().next();
			String authority = firstAuthority.getAuthority();
			
			return authority;
		}
	}
	
	// Ư�� ȸ���� �Խñ۸� ��ȸ�ϱ� ���� ȸ�� ���̵� �����ϱ� ���� �޼ҵ�
	// �α��� �������� �̹� Ż�� �� ���� Į���� üũ �߱� ������ �ٽ� üũ�� �ʿ� ���� �ٷ� id ����
	@GetMapping("/member/memberId")
	public ResponseEntity<?> getMemberId(HttpServletRequest request) {
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
			
			return ResponseEntity.ok().body(memberId);
		}
	}
	
	@GetMapping("/member")
	public ResponseEntity<?> getMember(HttpServletRequest request){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			MemberDto memberDto = memberService.getMember(SecurityContextHolder.getContext().getAuthentication().getName());
			
			return ResponseEntity.ok().body(memberDto);
		}
	}
	
	@PutMapping("/member")
	public ResponseEntity<?> putMember(HttpServletRequest request, @RequestBody SignInDto memberInfo){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			memberService.putMember(memberInfo);

			return ResponseEntity.ok("Success");
		}
	}
	
	@PutMapping("/member/deletion")
	public ResponseEntity<?> deleteMember(HttpServletRequest request, @RequestBody Map<String, String> pw){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			String password = pw.get("pw");
			
			memberService.deleteMember(username, password);
			
			return ResponseEntity.ok().body("Success");
		}
	}
	
	@GetMapping("/members")
	public ResponseEntity<?> getMembers(
			HttpServletRequest request, 
			@RequestParam("memberId") String memberId,
			@RequestParam("nickname") String nickname,
			@RequestParam("email") String email,
			Pageable pageable){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			Page<DetailedMemberDto> detailedMemberDtos = memberService.getMembers(memberId, nickname, email, pageable);

			return ResponseEntity.ok().body(detailedMemberDtos);
		}
	}
	
	@PutMapping("/member/ban")
	public ResponseEntity<?> updateBanDate(HttpServletRequest request, @RequestBody Map<String, String> orders){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			String memberId = orders.get("memberId");
			String order = orders.get("order");
			
			memberService.updateBanDate(memberId, order);
			
			return ResponseEntity.ok().body("Success");
		}
	}
	
}
