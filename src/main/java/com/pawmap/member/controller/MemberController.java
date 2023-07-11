package com.pawmap.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawmap.member.dto.MemberBanDto;
import com.pawmap.member.dto.MemberDto;
import com.pawmap.member.repository.MemberRepository;
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
	
	@Autowired
	private MemberRepository memberRepository;
	
	// ȸ������ ���� �޼ҵ�
	// ȸ������ ��ȸ �� ���
	@GetMapping("/member")
	public ResponseEntity<?> getMember(HttpServletRequest request){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			// ����� �ִ� accessToken ����
			// JwtAuthenticationFilter ���� ���� ��ȿ�� ��ū�� �ƴ� ��� ���̵�� anonymousUser�̰� ����Ʈ����� �޽��� ����
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			// ��ȿ�� accessToken�� ��� ���� �޼ҵ� ȣ��
			MemberDto memberDto = memberService.getMember(); // MemberDto = ȸ������ dto
			
			return ResponseEntity.ok().body(memberDto);
		}
	}
	
	// Ư�� ȸ���� �Խñ۸� ��ȸ�ϱ� ���� ȸ�� ���̵� �����ϱ� ���� �޼ҵ�
	// �α��� �������� �̹� Ż�� �� ���� Į���� üũ �߱� ������ �ٽ� üũ�� �ʿ� ���� �ٷ� id ����
	@GetMapping("/member/member-id")
	public ResponseEntity<?> getMemberId(HttpServletRequest request) {
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			// ����� �ִ� accessToken ����
			// JwtAuthenticationFilter ���� ���� ��ȿ�� ��ū�� �ƴ� ��� ���̵�� anonymousUser�̰� ����Ʈ����� �޽��� ����
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder���� ȸ�� ���̵� ��������
			
			return ResponseEntity.ok().body(memberId); // ȸ�� ���̵� ����
		}
	}
	
	// ��й�ȣ ���� �޼ҵ�
	@PutMapping("/member/pw")
	public ResponseEntity<?> putMemberPw(@RequestBody MemberDto memberDto, HttpServletRequest request){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			// ����� �ִ� accessToken ����
			// JwtAuthenticationFilter ���� ���� ��ȿ�� ��ū�� �ƴ� ��� ���̵�� anonymousUser�̰� ����Ʈ����� �޽��� ����
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			// ��ȿ�� accessToken�� ��� ���� �޼ҵ� ȣ��
			memberService.putMemberPw(memberDto);

			return ResponseEntity.ok().build(); // ���� �ڵ� 200(OK) ����
		}
	}
	
	// ȸ��Ż�� �޼ҵ�
	// ȸ�� ���̺��� Ż��¥(deletionDate) Į���� ������ �� Į���� ���� null�̸� �̿밡�� ȸ��, ��¥ ������(Ż��¥)�� ������ Ż���� ȸ��
	@PutMapping("/member/deletion-date")
	public ResponseEntity<?> putMemberDeletionDate(@RequestBody MemberDto memberDto, HttpServletRequest request){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			// ����� �ִ� accessToken ����
			// JwtAuthenticationFilter ���� ���� ��ȿ�� ��ū�� �ƴ� ��� ���̵�� anonymousUser�̰� ����Ʈ����� �޽��� ����
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			// ��ȿ�� accessToken�� ��� ���� �޼ҵ� ȣ��
			memberService.putMemberDeletionDate(memberDto);
			
			return ResponseEntity.ok().build(); // ���� �ڵ� 200(OK) ����
		}
	}
	
	// ȸ�� ���� �޼ҵ�
	// ȸ�� ���̺��� ���ܳ�¥(banDate) Į���� ������ �� Į���� ���� null�̸� �̿밡�� ȸ��, ��¥ ������(���ܳ�¥)�� ������ ���ܵ� ȸ��
	@PutMapping("/member/ban-date")
	public ResponseEntity<?> putMemberBanDate(@RequestBody MemberBanDto memberBanDto, HttpServletRequest request){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			// ����� �ִ� accessToken ����
			// JwtAuthenticationFilter ���� ���� ��ȿ�� ��ū�� �ƴ� ��� ���̵�� anonymousUser�̰� ����Ʈ����� �޽��� ����
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {		
			// ��ȿ�� accessToken�� ��� ���� �޼ҵ� ȣ��
			memberService.putMemberBanDate(memberBanDto);
			
			return ResponseEntity.ok().build(); // ���� �ڵ� 200(OK) ����
		}
	}
	
	// ȸ��Ż�� �� �ش� ȸ���� ����ߴ� ��� �ϸ�ũ�� �����ϴ� �޼ҵ�
	// ȸ��Ż�� ������ accessToken ���� ������ �ֱ� ������ ���� ���� �ٷ� ���� �޼ҵ� ȣ��
	@DeleteMapping("/member/bookmarks")
	public ResponseEntity<?> deleteBookmarks(HttpServletRequest request){
		memberService.deleteBookmarks();
		
		return ResponseEntity.ok().build(); // ���� �ڵ� 200(OK) ����
	}
	
	// ������ �������� ȸ������ �ǿ��� ȸ�� ��ȸ �� �˻�(����) ����� �����ϴ� �޼ҵ�
	@GetMapping("/members")
	public ResponseEntity<?> getMembers(
			@RequestParam("memberId") String memberId, 
			@RequestParam("nickname") String nickname, 
			@RequestParam("email") String email, 
			HttpServletRequest request, 
			Pageable pageable){
		if(SecurityContextHolder.getContext().getAuthentication().getName() == "anonymousUser") {
			// ����� �ִ� accessToken ����
			// JwtAuthenticationFilter ���� ���� ��ȿ�� ��ū�� �ƴ� ��� ���̵�� anonymousUser�̰� ����Ʈ����� �޽��� ����
			return ResponseEntity.ok().body("invalidAccessToken");
		}else {
			// ��ȿ�� accessToken�� ��� ���� �޼ҵ� ȣ��
			Page<MemberDto> memberDtos = memberService.getMembers(memberId, nickname, email, pageable);

			return ResponseEntity.ok().body(memberDtos); // ȸ������ ������ ����
		}
	}
	
	// �Է��� ȸ�� ���̵��� ���� ���� => ȸ������ �� �̿밡���� ���̵� ���� Ȯ���ϴ� �� ���
	@GetMapping("/member/member-id/number")
	public Long getMemberIdNumber(@RequestParam String memberId) {
		Long number = memberRepository.countByMemberId(memberId); // ���̵�� ��ȸ�Ͽ� ī��Ʈ ����

		return number;
	}
	
	// �Է��� �г����� ���� ���� => ȸ������ �� �̿밡���� �г��� ���� Ȯ���ϴ� �� ���
	@GetMapping("/member/nickname/number")
	public Long getNicknameNumber(@RequestParam String nickname) {
		Long number = memberRepository.countByNickname(nickname); // �г������� ��ȸ�Ͽ� ī��Ʈ ����

		return number;
	}
	
	// �Է��� ���� �ּ��� ���� ���� => ȸ������ �� �̿밡���� �̸��� ���� Ȯ���ϴ� �� ���
	@GetMapping("/member/email/number")
	public Long getEmailNumber(@RequestParam String email) {
		// Ż�� ȸ�� �簡�� ��� O => deletionDate Į�� �� null�� �ƴϾ ��
		// ���� ȸ�� �簡�� ��� X => banDate Į�� �� null�̾�� �� (null�� �ƴϸ� ���� ȸ���̹Ƿ� �ش� �̸��� ��� �Ұ�)
		Long number = memberRepository.countByEmailAndBanDate(email, null); // ���� �ּ�, ���� ��¥�� ��ȸ�Ͽ� ī��Ʈ ����

		return number;
	}
	
}
