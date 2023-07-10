package com.pawmap.member.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pawmap.member.dto.AuthDto;
import com.pawmap.member.dto.MemberDto;
import com.pawmap.member.service.AuthService;

// accessToken ��߱�, �̸��� ���� �ڵ� �߼� �� ����, �α���, ȸ������ �� ȸ�� ������ ������ ��Ʈ�ѷ�

@Controller
@RequestMapping("api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	// ȸ������ �� ȸ�� �̸��Ϸ� �̸��� ���� �ڵ带 �߼��ϰ� �����ڵ� �˻縦 ���� ����Ʈ����� �����ڵ带 �����ϴ� �޼ҵ�
	@GetMapping("/emailAuthCode")
	public String getEmailAuthCode(@RequestParam String email) {
		String emailAuthCode = authService.getEmailAuthCode(email); // ���� ���� �߼� �� �����ڵ� ��������

		return emailAuthCode; // �����ڵ� ����
	}
	
	// ȸ������ �޼ҵ�
	@PostMapping("/member")
	public ResponseEntity<?> postMember(@RequestBody MemberDto memberDto) {
		authService.postMember(memberDto); // ȸ������ ���� �޼ҵ� ȣ��
		
		return ResponseEntity.ok().build(); // ���� �ڵ� 200(OK) ����
	}

	// �α��� �޼ҵ�
	@GetMapping("/authorization")
	public ResponseEntity<?> getAuthorization(@RequestBody MemberDto memberDto, HttpServletResponse response) {
		AuthDto authDto = authService.getAuthorization(memberDto); // memberDto�� �α��� ���� �޼ҵ� ȣ��
		
		// ��Ű ����
		// data�� ����ִ� refreshToken ��Ű�� ���
		ResponseCookie cookie = ResponseCookie.from("refreshToken", authDto.getRefreshToken())
				//.maxAge(Duration.ofMinutes(1)) // �鿣�� ���ҽ� ��û �� accessToken �� refreshToken�� ��ȿ���� Ȯ���ϹǷ� cookie ���� �ð������� �������� ����
				.path("/") // ����Ʈ���� ������ ��Ű�� ����� path ����
				.secure(true) // https ����� ������
				.sameSite("none") // �ٸ� ���������� ������ �� ���
				.httpOnly(true) // http �Ǵ� https�� ���� ����Ͽ� �ڹٽ�ũ��Ʈ���� ��Ű�� �����Ͽ� �����ϴ� �� ����
				.build();

		response.setHeader("Set-cookie", cookie.toString()); // ��Ű ��� ����
		
		authDto.setRefreshToken(null); // authDto�� �ִ� refreshToken ����
		
		return ResponseEntity.ok(authDto); // refreshToken�� ������ authority, accessToken ����
	}
	
	// accessToken ��߱� �޼ҵ�
		// refreshToken�� Cookie�� ���� (������ ���� refreshToken�� Cookie�� ��� �ۼ��� <=> path, secure, sameStie, httpOnly)
		@GetMapping("/accessToken")
		public ResponseEntity<?> getAccessToken(@CookieValue("refreshToken") String refreshToken){
			String accessToken = authService.getAccessToken(refreshToken); // ������ refreshToken���� accessToken ��߱� �õ�
			
			if(accessToken.equals("invalidRefreshToken")) {
				// refreshToken�� ��ȿ���� �ʾƼ� accessToken ��߱� ������ ��� �޽��� ����
				return ResponseEntity.ok().body("invalidRefreshToken");
			}else {
				// authDto ��ü�� accessToken ��Ƽ� ����
				AuthDto authDto = new AuthDto();
				authDto.setAccessToken(accessToken);

				return ResponseEntity.ok().body(authDto);
			}	
		}

}
