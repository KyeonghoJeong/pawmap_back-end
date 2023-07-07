package com.pawmap.member.dao;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.pawmap.member.dto.SignInDto;
import com.pawmap.member.entity.MemberEntity;
import com.pawmap.member.repository.MemberRepository;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public void signUp(MemberEntity memberEntity) {
		// TODO Auto-generated method stub
		memberRepository.save(memberEntity);
	}

	// 회원정보 엔티티를 리턴하는 dao 메소드
	// deletionDate, banDate 속성이 모두 null이어야 현재 유효한 회원
	@Override
	public MemberEntity getMember(String memberId) {
		// TODO Auto-generated method stub
		Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberIdAndDeletionDateAndBanDate(memberId, null, null);
		
		MemberEntity memberEntity;
		
		if(optionalMemberEntity != null) {
			memberEntity = optionalMemberEntity.get();
			
			return memberEntity;
		}else {
			return null;
		}
	}

	@Override
	public void putMember(SignInDto memberInfo) {
		// TODO Auto-generated method stub
		memberRepository.putMember(memberInfo.getMemberId(), memberInfo.getPw());
	}

	@Override
	public void deleteMember(String memberId, Date deletionDate) {
		// TODO Auto-generated method stub
		memberRepository.deleteByMember(memberId, deletionDate);
	}

	@Override
	public Page<MemberEntity> getMembers(String memberId, String nickname, String email, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<MemberEntity> memberEntities = null;
		
		if(memberId.equals("") && nickname.equals("") && email.equals("")) {
			memberEntities = memberRepository.findByDeletionDate(null, pageable);
		}else if(!memberId.equals("") && nickname.equals("") && email.equals("")) {
			memberEntities = memberRepository.getMembersByMemberId(memberId, pageable);
		}else if(memberId.equals("") && !nickname.equals("") && email.equals("")) {
			memberEntities = memberRepository.getMembersByNickname(nickname, pageable);
		}else if(memberId.equals("") && nickname.equals("") && !email.equals("")) {
			memberEntities = memberRepository.getMembersByEmail(email, pageable);
		}
	
		return memberEntities;
	}

	@Override
	public void updateBanDate(String memberId, Date banDate) {
		// TODO Auto-generated method stub
		memberRepository.updateBanDate(memberId, banDate);
	}
	
}
