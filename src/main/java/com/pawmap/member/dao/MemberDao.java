package com.pawmap.member.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.member.entity.MemberEntity;

public interface MemberDao {

	MemberEntity getMember(String memberId);
	
	void putMemberPw(String memberId, String pw);
	
	void putMemberDeletionDate(String memberId, Date deletionDate);
	
	void putMemberBanDate(String memberId, Date banDate);
	
	void deleteBookmarks(String memberId);

	Page<MemberEntity> getMembers(String memberId, String nickname, String email, Pageable pageable);

}
