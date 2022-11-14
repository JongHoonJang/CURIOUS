package com.ssafy.metroverse.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ssafy.metroverse.global.filter.JwtAuthenticationFilter;
import com.ssafy.metroverse.global.handler.JwtAuthenticationEntryPoint;
import com.ssafy.metroverse.user.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
	private final JwtTokenProvider jwtTokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	// authenticationManager를 Bean 등록
	@Bean
	public AuthenticationManager authenticationManagerBean(
		AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers(
				"/swagger-ui/**",
				"/swagger-resources/**",
				"/v2/api-docs/**",
				"/webjars/**",
				"/favicon.com");
	}

	// 패스워드 인코더
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // rest api이므로 기본설정 미사용
		// 일반적인 루트가 아닌 다른 방식으로 요청 시 거절
		// header에 id, pw가 아닌 token(jwt)를 달고 감. 그래서 basic이 아닌 bearer를 사용
		http.httpBasic().disable() // rest api이므로 csrf 보안 미사용
			.cors()
			.and()
			.authorizeRequests() // 요청에 대한 사용권한 체크
			.antMatchers("/", "/users/KAKAO/callback/**", "/users/NAVER/callback/**", "/users/GOOGLE/callback/**",
				"/api/users/reissue").permitAll()
			.anyRequest().authenticated()
			.and()
			// 인증에 관한 예외처리
			.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.and()
			// JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
				UsernamePasswordAuthenticationFilter.class);
		// .addFilterBefore(jwtE); // jwt 필터 추가
		// 토큰에 저장된 유저정보를 활용하여야 하기 때문에 CustomUserDetailService 클래스를 생성 (?)
		// jwt로 인증하므로 세션 미사용
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}
}
