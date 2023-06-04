package com.pawmap.member.service;

import com.pawmap.member.dto.MemberDto;

public interface MemberService {

	void signUp(MemberDto memberInfo);

	String[] signIn(String username, String password);

	String getAccessToken(String refreshToken);

}
