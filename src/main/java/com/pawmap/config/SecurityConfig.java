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
			.authorizeRequests() // ��û ���� �ο� => ȸ��, �����ڸ� ���� ������ API�� anyRequest().authenticated()�� ��� ���Խ�Ŵ
				// APIs in Board
				.antMatchers(HttpMethod.GET, "/api/board/articles").permitAll() // GET => ��� ����
				.antMatchers(HttpMethod.DELETE, "/api/board/articles").hasRole("ADMIN") // DELETE => �����ڸ� ����
				.antMatchers("/api/board/article/comments").permitAll() // GET => ��� ����
				.antMatchers("/api/board/article/comment/numbers").permitAll() // GET => ��� ����
				.antMatchers("/api/board/article").permitAll() // GET => ��� ����
				// DistrictController
				.antMatchers("/api/map/district").permitAll() // sido, sigungu, emd => GET => ��� ����						
				// FacilityController
				.antMatchers("/api/map/facility").permitAll() // information, locations => GET => ��� ����
				.antMatchers("/api/map/facilities").permitAll() // GET => ��� ����				
				// AuthController
				.antMatchers("/api/auth/email-auth-code").permitAll() // GET => ��� ����
				.antMatchers("/api/auth/member").permitAll() // POST => ��� ����
				.antMatchers("/api/auth/sign-in").permitAll() // POST => ��� ����			
				// MemberController
				.antMatchers("/api/members").hasRole("ADMIN") // GET => �����ڸ� ����
				.antMatchers("/api/member/ban-date").hasRole("ADMIN") // PTT => �����ڸ� ����									
				.antMatchers("/api/member/member-id/number").permitAll() // GET => ��� ����
				.antMatchers("/api/member/nickname/number").permitAll() // GET => ��� ����
				.antMatchers("/api/member/email/number").permitAll() // GET => ��� ����
				.anyRequest().authenticated()
				.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session�� stateless�� ����
				.and()
			.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // ���� �߰�
		http
			.cors(); // cors ����
		
		return http.build();
	}
	
	// CORS ���� �޼ҵ�
	// ��������Ʈ(��Ʈ�ѷ� �޼ҵ� url) �� ��� ��ó(origin), HTTP �޼ҵ�, Header �̸�, Credential ����
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