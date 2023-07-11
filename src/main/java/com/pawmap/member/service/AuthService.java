package com.pawmap.member.service;

import com.pawmap.member.dto.AuthDto;
import com.pawmap.member.dto.MemberDto;

public interface AuthService {

	String getEmailAuthCode(String email);
	
	void postMember(MemberDto memberDto);
	
	AuthDto signIn(MemberDto memberDto);

	String getAccessToken(String refreshToken);

}
