package com.pawmap.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ModelMapper 클래스는 DTO ↔ Entity 매핑을 쉽게 할 수 있게 해줌
// 자주 사용하므로 Bean 등록해서 사용

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper(); // 객체 생성
		
		modelMapper.getConfiguration().setFieldMatchingEnabled(true); // 변수 이름으로 매칭
		modelMapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE); // private 필드 접근 허용 <= Entity 클래스는 setter가 없으므로 접근 허용
		
		return modelMapper;
	}
	
}