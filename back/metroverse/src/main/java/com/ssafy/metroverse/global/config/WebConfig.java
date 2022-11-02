package com.ssafy.metroverse.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:8080", "https://k7b107.p.ssafy.io")//, "https://helloworld.ssafy.io")
			.allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS")
			.allowedHeaders("Authorization", "Content-Type", "accept", "access-control-allow-origin")
			.exposedHeaders("Authorization") // JWT 로그인을 위해 클라이언트에서 Authorization 헤더에 접근 가능하도록
			.allowCredentials(true) // 클라이언트에서 쿠키를 받기 위해
			.maxAge(86400L);
	}
}
