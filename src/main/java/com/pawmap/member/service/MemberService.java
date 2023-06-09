package com.pawmap.member.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.member.dto.MemberBanDto;
import com.pawmap.member.dto.MemberDto;

public interface MemberService {

	MemberDto getMember();
	
	void deleteMemberArticles(List<Long> articleIds);
	
	void putMemberPw(MemberDto memberDto);
	
	boolean putMemberDeletionDate(MemberDto memberDto);
	
	void putMemberBanDate(MemberBanDto memberBanDto);
	
	void deleteBookmarks();

	Page<MemberDto> getMembers(String memberId, String nickname, String email, Pageable pageable);

}
