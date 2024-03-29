package com.pawmap.member.controller;

import java.util.List;

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
import com.pawmap.member.dto.MemberPwDto;
import com.pawmap.member.repository.MemberRepository;
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
	
	@Autowired
	private MemberRepository memberRepository;
	
	// 회원정보 리턴 메소드
	// 회원정보 조회 시 사용
	@GetMapping("/member")
	public ResponseEntity<?> getMember(HttpServletRequest request){
		// JwtAuthenticationFilter에서 accessToken이 유효하지 않거나 권한이 맞지 않는 경우 403 코드 리턴
		// accessToken이 유효한 경우 서비스 메소드 호출
		MemberDto memberDto = memberService.getMember(); // MemberDto = 회원정보 dto
		
		return ResponseEntity.ok().body(memberDto);
	}
	
	// 회원이 마이페이지에서 자신의 게시글들을 삭제하는 메소드
	@DeleteMapping("/member/articles")
	public ResponseEntity<?> deleteMemberArticles(@RequestParam List<Long> articleIds, HttpServletRequest request){
		// JwtAuthenticationFilter에서 accessToken이 유효하지 않거나 권한이 맞지 않는 경우 403 코드 리턴
		// accessToken이 유효한 경우 서비스 메소드 호출
		memberService.deleteMemberArticles(articleIds);
		
		return ResponseEntity.ok().build(); // 응답 코드 200(OK) 리턴
	}
	
	// Front-end 마이페이지의 내 게시글 탭에서 해당 회원의 게시글만 조회하기 위해 회원 아이디를 수신하기 위한 메소드
	// 로그인 과정에서 이미 탈퇴 및 차단 칼럼을 체크 했기 때문에 다시 체크할 필요 없이 바로 id 리턴
	@GetMapping("/member/member-id")
	public ResponseEntity<?> getMemberId(HttpServletRequest request) {
		// JwtAuthenticationFilter에서 accessToken이 유효하지 않거나 권한이 맞지 않는 경우 403 코드 리턴
		// accessToken이 유효한 경우 SecurityContext에서 아이디 가져와서 리턴
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder에서 회원 아이디 가져오기
		
		return ResponseEntity.ok().body(memberId); // 회원 아이디 리턴
	}
	
	// 비밀번호 수정 메소드
	@PutMapping("/member/pw")
	public ResponseEntity<?> putMemberPw(@RequestBody MemberPwDto memberPwDto, HttpServletRequest request){
		// JwtAuthenticationFilter에서 accessToken이 유효하지 않거나 권한이 맞지 않는 경우 403 코드 리턴
		// accessToken이 유효한 경우 서비스 메소드 호출
		boolean response = memberService.putMemberPw(memberPwDto);

		return ResponseEntity.ok().body(response); // 응답 코드 200(OK) 리턴 => 데이터로는 성공 시 true, 기존 비밀번호 불일치 시 false 리턴
	}
	
	// 회원탈퇴 메소드
	// 회원 테이블에는 탈퇴날짜(deletionDate) 칼럼이 있으며 이 칼럼의 값이 null이면 이용가능 회원, 날짜 데이터(탈퇴날짜)가 있으면 탈퇴한 회원
	@PutMapping("/member/deletion-date")
	public ResponseEntity<?> putMemberDeletionDate(@RequestBody MemberDto memberDto, HttpServletRequest request){
		// JwtAuthenticationFilter에서 accessToken이 유효하지 않거나 권한이 맞지 않는 경우 403 코드 리턴
		// accessToken이 유효한 경우 서비스 메소드 호출
		if(memberService.putMemberDeletionDate(memberDto)) {
			return ResponseEntity.ok().build(); // 응답 코드 200(OK) 리턴
		}else {
			return ResponseEntity.ok().body("incorrectPassword"); // 응답 코드 200(OK) 리턴
		}
	}
	
	// 회원 차단 메소드
	// 회원 테이블에는 차단날짜(banDate) 칼럼이 있으며 이 칼럼의 값이 null이면 이용가능 회원, 날짜 데이터(차단날짜)가 있으면 차단된 회원
	@PutMapping("/member/ban-date")
	public ResponseEntity<?> putMemberBanDate(@RequestBody MemberBanDto memberBanDto, HttpServletRequest request){
		// JwtAuthenticationFilter에서 accessToken이 유효하지 않거나 권한이 맞지 않는 경우 403 코드 리턴
		// accessToken이 유효한 경우 서비스 메소드 호출
		memberService.putMemberBanDate(memberBanDto);
					
		return ResponseEntity.ok().build(); // 응답 코드 200(OK) 리턴
	}
	
	// 회원탈퇴 후 해당 회원이 등록했던 모든 북마크를 삭제하는 메소드
	// 회원탈퇴 과정에 accessToken 인증 과정이 있기 때문에 인증 없이 바로 서비스 메소드 호출
	@DeleteMapping("/member/bookmarks")
	public ResponseEntity<?> deleteBookmarks(HttpServletRequest request){
		memberService.deleteBookmarks();
		
		return ResponseEntity.ok().build(); // 응답 코드 200(OK) 리턴
	}
	
	// 관리자 페이지의 회원관리 탭에서 회원 조회 시 검색(조건) 결과를 리턴하는 메소드
	@GetMapping("/members")
	public ResponseEntity<?> getMembers(
			@RequestParam("memberId") String memberId, 
			@RequestParam("nickname") String nickname, 
			@RequestParam("email") String email, 
			HttpServletRequest request, 
			Pageable pageable){
		// JwtAuthenticationFilter에서 accessToken이 유효하지 않거나 권한이 맞지 않는 경우 403 코드 리턴
		// accessToken이 유효한 경우 서비스 메소드 호출
		Page<MemberDto> memberDtos = memberService.getMembers(memberId, nickname, email, pageable);

		return ResponseEntity.ok().body(memberDtos); // 회원정보 페이지 리턴
	}
	
	// 입력한 회원 아이디의 수를 리턴 => 회원가입 시 이용가능한 아이디 여부 확인하는 데 사용
	@GetMapping("/member/member-id/number")
	public Long getMemberIdNumber(@RequestParam String memberId) {
		Long number = memberRepository.countByMemberId(memberId); // 아이디로 조회하여 카운트 리턴

		return number;
	}
	
	// 입력한 닉네임의 수를 리턴 => 회원가입 시 이용가능한 닉네임 여부 확인하는 데 사용
	@GetMapping("/member/nickname/number")
	public Long getNicknameNumber(@RequestParam String nickname) {
		Long number = memberRepository.countByNickname(nickname); // 닉네임으로 조회하여 카운트 리턴

		return number;
	}
	
	// 입력한 메일 주소의 수를 리턴 => 회원가입 시 이용가능한 이메일 여부 확인하는 데 사용
	@GetMapping("/member/email/number")
	public Long getEmailNumber(@RequestParam String email) {
		Long number = memberService.getEmailNumber(email); // 이메일 조회를 위해 서비스 메소드 호출
		
		return number;
	}
	
}
