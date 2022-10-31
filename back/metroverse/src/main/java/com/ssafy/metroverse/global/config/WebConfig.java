package com.ssafy.metroverse.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:8080", "http://localhost:8081",
				"https://k7b107.p.ssafy.io")//, "https://helloworld.ssafy.io")
			.allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS")
			.allowedHeaders("Authorization", "Content-Type", "accept", "access-control-allow-origin")
			.exposedHeaders("Authorization")
			.allowCredentials(true)
			.maxAge(86400L);
	}
}
