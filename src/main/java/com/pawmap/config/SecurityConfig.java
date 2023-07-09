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

import com.pawmap.security.CustomAuthenticationProvider;
import com.pawmap.security.JwtAuthenticationFilter;

// Spring Security ���� Ŭ����

@Configuration
@EnableWebSecurity // Spring Security Ȱ��ȭ
public class SecurityConfig {

	// �鿣�� ���ҽ� ���� ���� ����
	// ȸ�� ���� �� �ΰ��� ���� jwt �� ���� ����
	// Ŀ���� ���� JwtAuthenticationFilter �߰�
	// ���� ��û �� JwtAuthenticationFilter�� ��
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf().disable() // ���� ������ stateless ���·� ������ ���� ������ �������� �ʰ� jwt�� ���� ���ҽ� ���� �� ������ �̷�����Ƿ� ������ ���� ���� ������ disable
			.formLogin().disable() // formLogin ��� jwt ���
			.httpBasic().disable() // httpBasic ��� jwt ���
			.authorizeRequests() // ��û ���� �ο�
				.antMatchers("/api/**").permitAll()
				//.antMatchers("/api/member/signin").permitAll()
				//.antMatchers("/api/map/bookmark").permitAll() // Post => ȸ��, �����ڸ� ����
				//.antMatchers("/api/map/bookmarks").permitAll() // GET, DELETE => ȸ��, �����ڸ� ����
				//.antMatchers("/api/map/district/**").permitAll() // sido, sigungu, emd => GET => ��� ����
				//.antMatchers("/api/map/facility/**").permitAll() // information, locations => GET => ��� ����
				//.antMatchers("/api/map/facilities").permitAll() // GET => ��� ����
				//.antMatchers(HttpMethod.GET, "/api/board/article").permitAll() // GET => ��� ����
				//.antMatchers(HttpMethod.POST, "/api/board/article").permitAll() // POST => ȸ��, �����ڸ� ����
				//.antMatchers(HttpMethod.PUT, "/api/board/article").permitAll() // PUT => ȸ��, �����ڸ� ����
				//.antMatchers(HttpMethod.DELETE, "/api/board/article").permitAll() // DELETE => ȸ��, �����ڸ� ����
				//.antMatchers("/api/board/article/memberId/identification").permitAll() // GET => ȸ��, �����ڸ� ����
				//.antMatchers(HttpMethod.GET, "/api/board/articles").permitAll() // GET => ��� ����
				//.antMatchers(HttpMethod.DELETE, "/api/board/articles").permitAll() // DELETE => �����ڸ� ����
				//.antMatchers(HttpMethod.POST, "/api/board/article/comment").permitAll() // POST => ȸ��, �����ڸ� ����
				//.antMatchers(HttpMethod.PUT, "/api/board/article/comment").permitAll() // PUT => ȸ��, �����ڸ� ����
				//.antMatchers(HttpMethod.DELETE, "/api/board/article/comment").permitAll() // DELETE => ȸ��, �����ڸ� ����
				//.antMatchers("/api/board/article/comments").permitAll() // GET => ��� ����
				//.antMatchers("/api/board/article/comment/numbers").permitAll() // GET => ��� ����
				.anyRequest().authenticated()
				.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session�� stateless�� ����
				.and()
			.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // ���� �߰�
			
		return http.build();
	}

	// ȸ������ �� ��й�ȣ ��ȣȭ�� ���� �޼ҵ�
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// AuthenticationManager ���
	// MemberServiceImpl���� ȸ�� ��ȿ�� �˻縦 ���� ���
	// AuthenticationManager => AuthenticationProvider�� ����
	// AuthenticationConfiguration�� ������ ��ť��Ƽ �ʱ�ȭ �� AuthenticationManager�� ������ ���� Ŭ����
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	// AuthenticationManager���� ��ȿ�� �˻縦 ���� ���� => authenticate �޼ҵ�� ��ȿ�� �˻�
	// ȸ�� �α��� �� ȸ�� ���� �� ������ ȸ���� Authentication ��ü�� ��ȯ�ϴ� Ŭ���� ��ü ���
	@Bean
	public AuthenticationProvider authenticationProvider() {
		CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider();
		
		return customAuthenticationProvider;
	}
	
}