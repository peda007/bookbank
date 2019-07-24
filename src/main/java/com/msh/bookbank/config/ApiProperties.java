package com.msh.bookbank.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 외부 API 환경 설정
 * EX) Kakao book API
 * @author moon
 * @since 2019.07.21.
 */
@Data
@Configuration
@ConfigurationProperties(prefix="api")
public class ApiProperties {
	
	private int connectionTimeout;
	
	private int pageSize;
	
}
