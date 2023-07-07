package com.pawmap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pawmap.member.security.CustomAuthenticationProvider;
import com.pawmap.member.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity // Spring Security Ȱ��ȭ
public class SecurityConfig {

	// �鿣�� ���ҽ� ���� ���� ����
	// ȸ�� ���� �� �ΰ��� ���� jwt �� ���� ����
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf().disable() // ���� ������ stateless ���·� ������ ���� ������ �������� �ʰ� jwt�� ���� ���ҽ� ���� �� ������ �̷�����Ƿ� disable
			.formLogin().disable() // formLogin ��� jwt ���
			.httpBasic().disable() // httpBasic ��� jwt ���
			.authorizeRequests()
				.antMatchers("/api/**").permitAll()
				.antMatchers("/api/facilities/availability").permitAll()
				.antMatchers("/api/board/articles").permitAll()
				.antMatchers("/api/board/articles/comments/numbers").permitAll()
				.antMatchers("/api/member/signin").permitAll()
				.antMatchers("/api/member/authority").permitAll()
				.antMatchers("/api/facility").permitAll()
				.antMatchers("/api/districts/**").permitAll()
				.antMatchers("/api/facilities").permitAll()
				.antMatchers("/api/facilities/locations").permitAll()
				.antMatchers("/api/bookmark").permitAll()
				.anyRequest().authenticated()
				.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session�� stateless�� ����
				.and()
			// Ŀ���� ���� JwtAuthenticationFilter �߰�
			// ���� ��û �� JwtAuthenticationFilter�� ��
			.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
			
		return http.build();
	}

	// ȸ������ �� ��й�ȣ ��ȣȭ�� ���� �޼ҵ�
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// AuthenticationManager ���
	// MemberServiceImpl���� ȸ�� ��ȿ�� �˻縦 ���� ���
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	// ȸ�� �α��� �� ȸ�� ���� �� ������ ȸ���� Authentication ��ü�� ��ȯ�ϴ� Ŭ���� ���
	@Bean
	public AuthenticationProvider authenticationProvider() {
		CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider();
		
		return customAuthenticationProvider;
	}
	
}