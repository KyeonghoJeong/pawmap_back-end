package com.pawmap.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// CORS ���� Ŭ����
// ��������Ʈ(��Ʈ�ѷ� �޼ҵ� url) �� ��� ��ó(origin), HTTP �޼ҵ�, Header �̸�, Credential ����

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