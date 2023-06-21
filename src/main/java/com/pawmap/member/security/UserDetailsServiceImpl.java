package com.pawmap.member.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pawmap.member.entity.MemberEntity;
import com.pawmap.member.repository.MemberRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		// username(아이디)로 memberRepository에서 해당하는 회원정보 가져오기
		// deletionDate, banDate는 현재 등록 회원인 경우 모두 null
		// 해당하는 회원정보가 없는 경우 예외 발생
		MemberEntity memberEntity = memberRepository.findByMemberIdAndDeletionDateAndBanDate(username, null, null)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return new UserDetailsImpl(memberEntity); // 회원 엔티티 반환
	}

}
