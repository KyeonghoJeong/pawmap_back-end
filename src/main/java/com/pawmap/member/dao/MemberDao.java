package com.pawmap.member.dao;

import com.pawmap.member.dto.SignInDto;
import com.pawmap.member.entity.MemberEntity;

public interface MemberDao {

	void signUp(MemberEntity memberEntity);

	MemberEntity getMember(String memberId);

	void putMember(SignInDto memberInfo);

}
