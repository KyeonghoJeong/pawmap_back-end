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

// Spring Security 설정 클래스

@Configuration
@EnableWebSecurity // Spring Security 활성화
public class SecurityConfig {

	// 백엔드 리소스 접근 권한 설정
	// 회원 인증 및 인가를 위한 jwt 및 필터 설정
	// 커스텀 필터 JwtAuthenticationFilter 추가
	// 인증 요청 시 JwtAuthenticationFilter로 감
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf().disable() // 현재 서버는 stateless 상태로 서버에 인증 정보를 저장하지 않고 jwt을 통해 리소스 접근 시 인증이 이루어지므로 서버에 인증 정보 저장을 disable
			.formLogin().disable() // formLogin 대신 jwt 사용
			.httpBasic().disable() // httpBasic 대신 jwt 사용
			.authorizeRequests() // 요청 권한 부여
				//.antMatchers("/api/**").permitAll()
				//.antMatchers("/api/member/signin").permitAll()
				.antMatchers("/api/map/bookmark").permitAll() // Post => 회원, 관리자만 가능
				.antMatchers("/api/map/bookmarks").permitAll() // Get, Delete => 회원, 관리자만 가능
				.antMatchers("/api/map/district/**").permitAll() // sido, sigungu, emd => Get => 모두 가능
				.antMatchers("/api/map/facility/**").permitAll() // information, locations => Get => 모두 가능
				.antMatchers("/api/map/facilities").permitAll() // Get => 모두 가능
				.anyRequest().authenticated()
				.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session은 stateless로 설정
				.and()
			.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // 필터 추가
			
		return http.build();
	}

	// 회원가입 시 비밀번호 암호화를 위한 메소드
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// AuthenticationManager 등록
	// MemberServiceImpl에서 회원 유효성 검사를 위해 사용
	// AuthenticationManager => AuthenticationProvider에 위임
	// AuthenticationConfiguration는 스프링 시큐리티 초기화 시 AuthenticationManager를 생성한 설정 클래스
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	// AuthenticationManager에게 유효성 검사를 위임 받음 => authenticate 메소드로 유효성 검사
	// 회원 로그인 시 회원 인증 및 인증된 회원의 Authentication 객체를 반환하는 클래스 객체 등록
	@Bean
	public AuthenticationProvider authenticationProvider() {
		CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider();
		
		return customAuthenticationProvider;
	}
	
}