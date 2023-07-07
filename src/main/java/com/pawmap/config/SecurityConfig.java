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
@EnableWebSecurity // Spring Security 활성화
public class SecurityConfig {

	// 백엔드 리소스 접근 권한 설정
	// 회원 인증 및 인가를 위한 jwt 및 필터 설정
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf().disable() // 현재 서버는 stateless 상태로 서버에 인증 정보를 저장하지 않고 jwt을 통해 리소스 접근 시 인증이 이루어지므로 disable
			.formLogin().disable() // formLogin 대신 jwt 사용
			.httpBasic().disable() // httpBasic 대신 jwt 사용
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
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session은 stateless로 설정
				.and()
			// 커스텀 필터 JwtAuthenticationFilter 추가
			// 인증 요청 시 JwtAuthenticationFilter로 감
			.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
			
		return http.build();
	}

	// 회원가입 시 비밀번호 암호화를 위한 메소드
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// AuthenticationManager 등록
	// MemberServiceImpl에서 회원 유효성 검사를 위해 사용
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	// 회원 로그인 시 회원 인증 및 인증된 회원의 Authentication 객체를 반환하는 클래스 등록
	@Bean
	public AuthenticationProvider authenticationProvider() {
		CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider();
		
		return customAuthenticationProvider;
	}
	
}