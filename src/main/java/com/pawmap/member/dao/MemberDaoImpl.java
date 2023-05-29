package com.pawmap.member.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
