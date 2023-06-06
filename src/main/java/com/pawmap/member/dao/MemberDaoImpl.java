package com.pawmap.member.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public MemberEntity getMember(String memberId) {
		// TODO Auto-generated method stub
		Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(memberId);
		
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

}
