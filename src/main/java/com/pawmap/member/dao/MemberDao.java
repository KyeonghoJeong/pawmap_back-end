package com.pawmap.member.dao;

import java.util.Date;

import com.pawmap.member.dto.SignInDto;
import com.pawmap.member.entity.MemberEntity;

public interface MemberDao {

	void signUp(MemberEntity memberEntity);

	MemberEntity getMember(String memberId);

	void putMember(SignInDto memberInfo);

	void deleteMember(String memberId, Date deletionDate);

}
