package com.pawmap.member.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawmap.board.dao.ArticleDao;
import com.pawmap.member.dao.MemberDao;
import com.pawmap.member.dto.MemberBanDto;
import com.pawmap.member.dto.MemberDto;
import com.pawmap.member.dto.MemberPwDto;
import com.pawmap.member.entity.MemberEntity;

@Service
@Transactional // 트랜잭션 설정
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private ArticleDao articleDao;

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
	
	// 회원이 마이페이지에서 자신의 게시글들을 삭제하는 메소드
	@Override
	public void deleteMemberArticles(List<Long> articleIds) {
		// TODO Auto-generated method stub
		articleDao.deleteArticles(articleIds);
	}
	
	@Override
	public boolean putMemberPw(MemberPwDto memberPwDto) {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder에서 회원 아이디 가져오기
		UserDetails userDetails = userDetailsService.loadUserByUsername(memberId); // 해당 아이디의 엔티티(회원정보) 가져오기
		
    	// 사용자가 입력한 비밀번호와 DB 테이블에 등록되어 있는 비밀번호 일치 확인
		if(!passwordEncoder.matches(memberPwDto.getPw(), userDetails.getPassword())) {
			return false; // 불일치 시 false 리턴
		}
		
		String newPw = passwordEncoder.encode(memberPwDto.getNewPw()); // 새 비밀번호 암호화
		
		memberDao.putMemberPw(memberId, newPw); // dao 호출
		
		return true; // 비밀번호 변경 성공 시 true 리턴
	}
	
	// 회원탈퇴 (탈퇴날짜 삽입) 메소드
	@Override
	public boolean putMemberDeletionDate(MemberDto memberDto) {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder에서 회원 아이디 가져오기
		String pw = memberDto.getPw(); // 탈퇴 시 입력한 비밀번호 가져오기
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(memberId); // 해당 아이디의 엔티티(회원정보) 가져오기
		
		if(passwordEncoder.matches(pw, userDetails.getPassword())){ // 입력한 비밀번호와 등록 비밀번호가 같은 경우
			Date deletionDate = Calendar.getInstance().getTime(); // 현재 날짜
			
			memberDao.putMemberDeletionDate(memberId, deletionDate); // dao 호출
			
			return true; // 비밀번호가 맞고 탈퇴 처리가 완료된 후 true 리턴
		}else {
			return false; // 비밀번호가 틀리면 false 리턴
		}
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

	// 회원가입 시 요청 이메일의 중복 확인 메소드
	@Override
	public Long getEmailNumber(String email) {
		// TODO Auto-generated method stub
		Long number = memberDao.getEmailNumber(email); // dao 호출

		return number;
	}

}
