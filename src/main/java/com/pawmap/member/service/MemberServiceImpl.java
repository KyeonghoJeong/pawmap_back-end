package com.pawmap.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pawmap.member.dao.MemberDao;
import com.pawmap.member.dto.MemberDto;
import com.pawmap.member.entity.MemberEntity;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void signUp(MemberDto memberInfo) {
		// TODO Auto-generated method stub
		MemberDto memberDto = new MemberDto();
		
		memberDto.setMemberId(memberInfo.getMemberId());
		memberDto.setPw(passwordEncoder.encode(memberInfo.getPw()));
		memberDto.setNickname(memberInfo.getNickname());
		memberDto.setEmail(memberInfo.getEmail());
		
		MemberEntity memberEntity = new MemberEntity(
				memberDto.getMemberId(),
				memberDto.getPw(),
				memberDto.getNickname(),
				memberDto.getEmail()
		);
		
		memberDao.signUp(memberEntity);
	}

}
