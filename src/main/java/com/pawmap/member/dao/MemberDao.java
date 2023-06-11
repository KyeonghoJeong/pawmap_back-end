package com.pawmap.member.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawmap.member.dto.SignInDto;
import com.pawmap.member.entity.MemberEntity;

public interface MemberDao {

	void signUp(MemberEntity memberEntity);

	MemberEntity getMember(String memberId);

	void putMember(SignInDto memberInfo);

	void deleteMember(String memberId, Date deletionDate);

	Page<MemberEntity> getMembers(String memberId, String nickname, String email, Pageable pageable);

	void updateBanDate(String memberId, Date banDate);

}
