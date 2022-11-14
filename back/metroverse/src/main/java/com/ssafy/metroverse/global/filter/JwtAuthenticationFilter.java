package com.ssafy.metroverse.global.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.ssafy.metroverse.user.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
		ServletException {
		// 헤더에서 JWT 받아옴
		String token = jwtTokenProvider.resolveToken((HttpServletRequest)request);
		// 유효한 토큰인지 확인
		if (token != null && jwtTokenProvider.validateToken(token)) {
			try {
				// 토큰이 유효하면 토큰으로부터 유저 정보를 받아옴
				Authentication authentication = jwtTokenProvider.getAuthentication(token);
				// SecurityContext 에 Authentication 객체를 저장
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (IllegalArgumentException e) {
				logger.error("an error occured during getting usesrname from token", e);
			}
			// catch (ExpiredJwtException e) { // 유효 기간이 지난 JWT를 수신한 경우
			// 	logger.warn("the token is expired and not valid anymore", e);
			// 	((HttpServletResponse)response).sendError(401, "ExpiredJwtException error");
			// } catch (SignatureException e) { // 서명 오류
			// 	logger.error("Authentication Failed. Username or Password not valid.");
			// 	((HttpServletResponse)response).sendError(401, "SignatureException error");
			// }
		} else {
			logger.warn("couldn't find bearer string, will ignore the header");
			//			((HttpServletResponse)response).sendError(401, "access token 만료");
		}
		chain.doFilter(request, response);
	}
}
