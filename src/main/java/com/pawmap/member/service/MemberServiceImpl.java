package com.pawmap.member.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawmap.member.dao.MemberDao;
import com.pawmap.member.dto.MemberBanDto;
import com.pawmap.member.dto.MemberDto;
import com.pawmap.member.entity.MemberEntity;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberDao memberDao;

	// 회원정보 리턴 서비스 메소드
	@Override
	public MemberDto getMember() {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder에서 회원 아이디 가져오기 
		
		MemberEntity memberEntity = memberDao.getMember(memberId); // 회원 아이디로 엔티티 가져오기
		
		MemberDto memberDto = modelMapper.map(memberEntity, MemberDto.class);
		memberDto.setPw(null);
		
		return memberDto;
	}
	
	@Override
	public void putMemberPw(MemberDto memberDto) {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder에서 회원 아이디 가져오기
		String pw = passwordEncoder.encode(memberDto.getPw()); // 비밀번호 암호화
		
		memberDao.putMemberPw(memberId, pw); // dao 호출
	}

	// 권한명 리턴 메소드
	@Override
	public String getMemberRole() {
		// TODO Auto-generated method stub
		
		// SecurityContextHolder에서 권한 가져오기
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
		GrantedAuthority authority = authorities.iterator().next(); // 이 어플리케이션에는 회원 당 권한이 하나
		String role = authority.getAuthority(); // role(권한명) 가져오기
		
		return role; // role 리턴
	}
	
	// 회원탈퇴 (탈퇴날짜 수정) 메소드
	@Override
	public void putMemberDeletionDate(MemberDto memberDto) {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder에서 회원 아이디 가져오기
		String pw = memberDto.getPw(); // 비밀번호 가져오기
		
		// 유효성 검사를 위해 authentication 토큰 객체 생성
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(memberId, pw);
		
		// CustomAuthenticationProvider 회원 유효성 검사
		// DB에 등록된 회원 아이디가 아니거나 입력한 비밀번호 인코딩 결과가 DB에 등록된 비밀번호와 같지 않으면 예외 발생  
		Authentication authenticatedMember = authenticationManager.authenticate(authentication); // authentication 객체 생성
		memberId = authenticatedMember.getName(); // 인증완료 된 회원 아이디
		
		Date deletionDate = Calendar.getInstance().getTime(); // 현재 날짜
		
		memberDao.putMemberDeletionDate(memberId, deletionDate);
	}
	
	// 회원차단 (차단날짜 수정) 메소드
	@Override
	public void putMemberBanDate(MemberBanDto memberBanDto) {
		// TODO Auto-generated method stub
		String memberId = memberBanDto.getMemberId(); // 회원 아이디
		String order = memberBanDto.getOrder(); // 차단 or 차단 해제 명령
		
		Date banDate = null;
		
		if(order.equals("ban")) {
			banDate = Calendar.getInstance().getTime(); // order의 값이 ban이면 현재 날짜 저장 아니면 (unban) null 저장
		}
		
		memberDao.putMemberBanDate(memberId, banDate); // dao 호출
	}
	
	// 탈퇴 회원의 모든 북마크 삭제 메소드
	@Override
	public void deleteBookmarks() {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder에서 회원 아이디 가져오기
		
		memberDao.deleteBookmarks(memberId); // dao 호출
	}

	@Override
	public Page<MemberDto> getMembers(String memberId, String nickname, String email, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<MemberEntity> memberEntities = memberDao.getMembers(memberId, nickname, email, pageable); // dao에 회원 엔티티 페이지 요청
		
		// Page 형 유지하면서 MemberEntity를 매개변수로 MemberDto에 설정한 생성자대로 매핑
		Page<MemberDto> memberDtos = memberEntities.map(MemberDto::new);
		
		return memberDtos;
	}

}
