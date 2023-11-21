package com.pawmap.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pawmap.member.entity.MemberEntity;

// UserDetails�� ȸ�� ������ ǥ��ȭ, ����, ����, Ȯ��, ����(Ŀ����)�� ���� �ʿ��� �������̽�
// ��ü�� ȸ�� ���� ��ƼƼ�� ����
// ȸ�� ����, ��й�ȣ, ���̵�, ���� ���� ����, ���� ��� ����, ��й�ȣ ���� ����, ���� ��� ���� ���� ����

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
