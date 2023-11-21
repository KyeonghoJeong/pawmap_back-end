package com.pawmap.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pawmap.member.entity.MemberEntity;

// UserDetails는 회원 정보의 표준화, 통합, 보안, 확장, 변경(커스텀)을 위해 필요한 인터페이스
// 객체는 회원 정보 엔티티를 갖음
// 회원 권한, 비밀번호, 아이디, 계정 만기 여부, 계정 잠금 여부, 비밀번호 만기 여부, 계정 사용 가능 여부 설정

public class UserDetailsImpl implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private MemberEntity memberEntity;

	public UserDetailsImpl(MemberEntity memberEntity) {
		// TODO Auto-generated constructor stub
		this.memberEntity = memberEntity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority(this.memberEntity.getRole()));

		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.memberEntity.getPw();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.memberEntity.getMemberId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
