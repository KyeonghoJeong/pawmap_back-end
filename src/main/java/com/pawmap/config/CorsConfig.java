package com.pawmap.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// CORS 설정 클래스
// 엔드포인트(컨트롤러 메소드 url) 별 허용 출처(origin), HTTP 메소드, Header 이름, Credential 설정

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:8080")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("*")
				.allowCredentials(true);
	}
	
}