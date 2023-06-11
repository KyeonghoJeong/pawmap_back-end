package com.pawmap.member.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.member.dto.DetailedMemberDto;
import com.pawmap.member.dto.MemberDto;
import com.pawmap.member.dto.SignInDto;

public interface MemberService {

	void signUp(MemberDto memberInfo);

	String[] signIn(String username, String password);

	String getAccessToken(String refreshToken);

	MemberDto getMember(String memberId);

	void putMember(SignInDto memberInfo);

	void deleteMember(String username, String password);

	Page<DetailedMemberDto> getMembers(String memberId, String nickname, String email, Pageable pageable);

	void updateBanDate(String memberId, String order);

}
