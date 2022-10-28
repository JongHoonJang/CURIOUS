package com.ssafy.metroverse.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.metroverse.global.common.Response;
import com.ssafy.metroverse.global.model.SocialType;
import com.ssafy.metroverse.oauth.service.OAuthService;
import com.ssafy.metroverse.user.JwtTokenProvider;
import com.ssafy.metroverse.user.dto.TokenRequest;
import com.ssafy.metroverse.user.dto.TokenResponse;
import com.ssafy.metroverse.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final JwtTokenProvider jwtTokenProvider;
	private final UserService userService;
	private final OAuthService oAuthService;
	private final Response response;

	@GetMapping("/{type}/callback")
	public ResponseEntity<?> login(@RequestParam(value = "code") String code,
		@PathVariable(value = "type") String socialType, HttpServletResponse res) {
		// authorizedCode : 카카오 서버로부터 받은 인가 코드
		TokenResponse tokenResponse = oAuthService.socialLogin(code, SocialType.valueOf(socialType.toUpperCase()));
		ResponseCookie cookie = ResponseCookie.from("refresh-token", tokenResponse.getRefreshToken())
			.maxAge(60 * 60 * 24 * 15)
			.httpOnly(true)
			.secure(true)
			.domain("")
			.path("/")
			.sameSite("None")
			.build();

		res.setHeader("Set-Cookie", cookie.toString());
		return response.success(tokenResponse);
	}

	@GetMapping("/reissue")
	public ResponseEntity<?> reissue(@CookieValue(value = "refresh-token", required = false) Cookie cookie,
		HttpServletResponse res) {
		System.out.println("cookie = " + cookie);

		TokenResponse tokenResponse = oAuthService.reissue(new TokenRequest("", cookie.getValue()));
		// JWToken jwt = authService.reissue(cookie.getValue());
		// System.out.println("jwt = " + jwt.getAccessToken());
		ResponseCookie newCookie = ResponseCookie.from("refresh-token", tokenResponse.getRefreshToken())
			.maxAge(60 * 60 * 24 * 15)
			.httpOnly(true)
			.secure(true)
			.domain("")
			.path("/")
			.sameSite("None")
			.build();

		res.setHeader("Set-Cookie", newCookie.toString());

		return response.success(tokenResponse);
	}
}
