package com.pawmap.member.dao;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.pawmap.map.repository.BookmarkRepository;
import com.pawmap.member.entity.MemberEntity;
import com.pawmap.member.repository.MemberRepository;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private BookmarkRepository bookmarkRepository;

	// 회원정보 엔티티를 리턴하는 dao 메소드
	// deletionDate, banDate 속성이 모두 null이어야 현재 유효한 회원
	// ArticleService, CommentService 클래스에서도 사용
	@Override
	public MemberEntity getMember(String memberId) {
		// TODO Auto-generated method stub
		
		// 회원 엔티티 생성
		// 회원 아이디로 조회
		// 탈퇴 날짜, 차단 날짜 둘 다 null => 현재 이용가능 회원
		Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberIdAndDeletionDateAndBanDate(memberId, null, null);
		
		MemberEntity memberEntity = null;
		
		if(optionalMemberEntity != null) {
			memberEntity = optionalMemberEntity.get(); // null이 아니면 값 가져오기
		}

		return memberEntity;
	}
	
	@Override
	public void putMemberPw(String memberId, String pw) {
		// TODO Auto-generated method stub
		memberRepository.putMemberPw(memberId, pw); // 회원 아이디로 조회하여 해당 row의 비밀번호 값 변경
	}
	
	@Override
	public void putMemberDeletionDate(String memberId, Date deletionDate) {
		// TODO Auto-generated method stub
		// deletionDate 칼럼 값에 날짜 데이터가 있으면 탈퇴 상태, null이면 이용가능 회원 => 해당 회원 아이디로 조회하여 탈퇴날짜 칼럼의 값 수정
		memberRepository.putMemberDeletionDate(memberId, deletionDate);
	}

	@Override
	public void putMemberBanDate(String memberId, Date banDate) {
		// TODO Auto-generated method stub
		// banDate 칼럼 값에 날짜 데이터가 있으면 차단 상태 null이면 이용가능 회원 => 해당 회원 아이디로 조회하여 차단날짜 칼럼의 값 수정
		memberRepository.putMemberBanDate(memberId, banDate);
	}
	
	@Override
	public void deleteBookmarks(String memberId) {
		// TODO Auto-generated method stub
		bookmarkRepository.deleteAllByMemberId(memberId); // 해당 회원 아이디로 조회하여 해당하는 모든 북마크 row 삭제
	}

	@Override
	public Page<MemberEntity> getMembers(String memberId, String nickname, String email, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<MemberEntity> memberEntities = null;
		
		// 각 파라미터 유무를 조건으로 repository 메소드 호출 => 값이 ""인 경우는 검색하지 않은 경우
		
		// 모든 회원 조회
		if(memberId.equals("") && nickname.equals("") && email.equals("")) {
			// deletionDate 값이 null인 경우는 탈퇴 회원으로 조회 시 제외해야 하므로 null인 row 조회
			//memberEntities = memberRepository.findByDeletionDate(null, pageable);
			memberEntities = memberRepository.getMembers(pageable);
		}
		
		// 회원 아이디로 검색
		if(!memberId.equals("") && nickname.equals("") && email.equals("")) {
			memberEntities = memberRepository.getMembersByMemberId(memberId, pageable);
		}
		
		// 닉네임으로 검색
		if(memberId.equals("") && !nickname.equals("") && email.equals("")) {
			memberEntities = memberRepository.getMembersByNickname(nickname, pageable);
		}
		
		// 이메일로 검색
		if(memberId.equals("") && nickname.equals("") && !email.equals("")) {
			memberEntities = memberRepository.getMembersByEmail(email, pageable);
		}
	
		return memberEntities;
	}

	@Override
	public Long getEmailNumber(String email) {
		// TODO Auto-generated method stub
		// 회원가입 요청 이메일의 중복을 확인할 때 관리자 권한 계정의 이메일과 중복되는지 먼저 확인하기
		Long number = memberRepository.getAdminEmailNumber(email);
		
		// 관리자 권한 계정 중에 해당 이메일을 사용하는 계정이 없는 경우에는 회원 계정 중에 있는지 확인
		if(number == 0) {
			// 탈퇴 회원 재가입 허용 O => deletionDate 칼럼 값 null이 아니어도 됨
			// 차단 회원 재가입 허용 X => banDate 칼럼 값 null이어야 함 (null이 아니면 차단 회원이므로 해당 이메일 사용 불가)
			number = memberRepository.getEmailNumber(email); // 메일 주소, 차단 날짜로 조회하여 카운트 리턴
			
			return number;
		}

		return number;
	}
	
}
