package com.pawmap.member.service;

import com.pawmap.member.dto.JwtTokenDto;
import com.pawmap.member.dto.MemberDto;

public interface MemberService {

	void signUp(MemberDto memberInfo);

	JwtTokenDto signIn(String username, String password);

}
