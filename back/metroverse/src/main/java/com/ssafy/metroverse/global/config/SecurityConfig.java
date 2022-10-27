package com.ssafy.metroverse.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ssafy.metroverse.global.filter.JwtAuthenticationFilter;
import com.ssafy.metroverse.user.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final JwtTokenProvider jwtTokenProvider;

	// authenticationManager를 Bean 등록
	// @Bean
	// public AuthenticationManager authenticationManagerBean(
	// 	AuthenticationConfiguration authenticationConfiguration) throws Exception {
	// 	return authenticationConfiguration.getAuthenticationManager();
	// }

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// @Bean
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// 일반적인 루트가 아닌 다른 방식으로 요청 시 거절
		// header에 id, pw가 아닌 token(jwt)를 달고 감. 그래서 basic이 아닌 bearer를 사용
		http.httpBasic().disable()
			.authorizeRequests() // 요청에 대한 사용권한 체크
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated()
			.and()
			// JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
				UsernamePasswordAuthenticationFilter.class);
		// 토큰에 저장된 유저정보를 활용하여야 하기 때문에 CustomUserDetailService 클래스를 생성
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// return http.build();
	}
}
