package com.pawmap.member.service;

import com.pawmap.member.dto.MemberDto;
import com.pawmap.member.dto.SignInDto;

public interface MemberService {

	void signUp(MemberDto memberInfo);

	String[] signIn(String username, String password);

	String getAccessToken(String refreshToken);

	MemberDto getMember(String memberId);

	void putMember(SignInDto memberInfo);

}
