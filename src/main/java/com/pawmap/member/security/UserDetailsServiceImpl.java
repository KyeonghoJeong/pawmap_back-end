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
		MemberEntity memberEntity = memberRepository.findByMemberIdAndDeletionDateAndBanDate(username, null, null)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return new UserDetailsImpl(memberEntity);
	}

}
