package com.pawmap.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
	// accessToken이 유효하지 않으면 403 에러 코드 리턴
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf().disable() // 현재 서버는 stateless 상태로 서버에 인증 정보를 저장하지 않고 jwt을 통해 리소스 접근 시 인증이 이루어지므로 disable
			.formLogin().disable() // formLogin 대신 jwt 사용
			.httpBasic().disable() // httpBasic 대신 jwt 사용
			.authorizeRequests() // 요청 권한 부여 => 회원, 관리자만 접근 가능한 API는 anyRequest().authenticated()로 모두 포함시킴
				// APIs in Board
				.antMatchers(HttpMethod.GET, "/api/board/articles").permitAll() // GET => 모두 가능
				.antMatchers(HttpMethod.DELETE, "/api/board/articles").hasRole("ADMIN") // DELETE => 관리자만 가능
				.antMatchers(HttpMethod.GET, "/api/board/article/comments").permitAll() // GET => 모두 가능
				.antMatchers(HttpMethod.GET, "/api/board/article/comment/numbers").permitAll() // GET => 모두 가능
				.antMatchers(HttpMethod.GET, "/api/board/article").permitAll() // GET => 모두 가능
				// DistrictController
				.antMatchers(HttpMethod.GET, "/api/map/district/**").permitAll() // sido, sigungu, emd => GET => 모두 가능						
				// FacilityController
				.antMatchers(HttpMethod.GET, "/api/map/facility/**").permitAll() // information, locations => GET => 모두 가능
				.antMatchers(HttpMethod.GET, "/api/map/facilities").permitAll() // GET => 모두 가능				
				// AuthController
				.antMatchers(HttpMethod.GET, "/api/auth/email-auth-code").permitAll() // GET => 모두 가능
				.antMatchers(HttpMethod.POST, "/api/auth/member").permitAll() // POST => 모두 가능
				.antMatchers(HttpMethod.POST, "/api/auth/sign-in").permitAll() // POST => 모두 가능	
				.antMatchers(HttpMethod.GET, "/api/auth/access-token").permitAll() // GET => 모두 가능
				// MemberController
				.antMatchers(HttpMethod.GET, "/api/members").hasRole("ADMIN") // GET => 관리자만 가능
				.antMatchers(HttpMethod.PUT, "/api/member/ban-date").hasRole("ADMIN") // PUT => 관리자만 가능									
				.antMatchers(HttpMethod.GET, "/api/member/member-id/number").permitAll() // GET => 모두 가능
				.antMatchers(HttpMethod.GET, "/api/member/nickname/number").permitAll() // GET => 모두 가능
				.antMatchers(HttpMethod.GET, "/api/member/email/number").permitAll() // GET => 모두 가능
				.antMatchers(HttpMethod.POST, "/api/member/pw").permitAll()
				.anyRequest().authenticated() // 회원, 관리자만 접근 가능한 API는 anyRequest().authenticated()로 모두 포함시킴
				.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session은 stateless로 설정
				.and()
			.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // 필터 추가
		http
			.cors(); // cors 설정
		
		return http.build();
	}
	
	// CORS 설정 메소드
	// 엔드포인트(컨트롤러 메소드 url) 별 허용 출처(origin), HTTP 메소드, Header 이름, Credential 설정
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		
		configuration.addAllowedOrigin("http://localhost:8080");
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.addAllowedHeader("*");
		configuration.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

	// 회원가입 시 비밀번호 암호화를 위한 메소드
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// AuthenticationManager 등록
	// AuthServiceImpl에서 회원 유효성 검사를 위해 사용
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