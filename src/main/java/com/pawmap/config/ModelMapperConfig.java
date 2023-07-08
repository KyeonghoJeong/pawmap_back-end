package com.pawmap.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ModelMapper Ŭ������ DTO �� Entity ������ ���� �� �� �ְ� ����
// ���� ����ϹǷ� Bean ����ؼ� ���

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper(); // ��ü ����
		
		modelMapper.getConfiguration().setFieldMatchingEnabled(true); // ���� �̸����� ��Ī
		modelMapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE); // private �ʵ� ���� ��� <= Entity Ŭ������ setter�� �����Ƿ� ���� ���
		
		return modelMapper;
	}
	
}