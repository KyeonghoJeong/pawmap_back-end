package com.pawmap.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

// AuthenticationProvider Ŀ���� ���� Ŭ����

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// ȸ���� ���̵� ����Ͽ� �ش��ϴ� ȸ�������� Ȯ��
	// �ش� ȸ���� ��й�ȣ�� �Է��� ��й�ȣ�� ��ġ�ϴ� �� Ȯ��
	// ��ġ �� ������ Authentication ��ü ���� �� ����
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		// ���� ���� authentication���� ���̵�� ��й�ȣ ����
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
	    try {
	    	UserDetails userDetails = userDetailsService.loadUserByUsername(username); // �ش� ���̵��� ��ƼƼ(ȸ������) ��������
	    	
	    	// ����Ʈ���� ����ڰ� �Է��� ��й�ȣ�� DB ���̺� ��ϵǾ� �ִ� ��й�ȣ ��ġ Ȯ��
	    	// �ٸ� ��� ���� �߻�
			if(!passwordEncoder.matches(password, userDetails.getPassword())) {
				log.info("password is not matched");
				throw new BadCredentialsException("password is not matched");
			}
			
			// ������ ȸ�� Authentication ��ü ���� �� ����
			// �Ķ���� ���� ȸ�� ��ƼƼ, ��й�ȣ (���� ���� �ʿ� �����Ƿ� null), ȸ�� ����
			Authentication authenticatedMember = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
		    return authenticatedMember;
	    } catch (UsernameNotFoundException e) { // userDetailsService���� �߻��� UsernameNotFoundException ó��
	    	log.info("username is not found");
	        throw new BadCredentialsException("username is not found");
	    }
	}

	// ȸ�� �α��� �� CustomAuthenticationProvider�� ����ǰ� supports �޼ҵ尡 ȣ���
	// �Ű������� authenticaiton ��ü�� UsernamePasswordAuthenticationToken Ŭ������ �����ϴ� �� Ȯ�� �� true or false ����
	// true�� ��� authenticate�� ���� ��
	// false�� ��� authenticate�� ������� �ʰ� CustomAuthenticationProvider�� ��ŵ��
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}