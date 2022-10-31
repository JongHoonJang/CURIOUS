package com.ssafy.metroverse.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.metroverse.global.common.Response;
import com.ssafy.metroverse.global.model.SocialType;
import com.ssafy.metroverse.oauth.service.OAuthService;
import com.ssafy.metroverse.user.JwtTokenProvider;
import com.ssafy.metroverse.user.dto.TokenRequest;
import com.ssafy.metroverse.user.dto.TokenResponse;
import com.ssafy.metroverse.user.dto.UserLoginResponse;
import com.ssafy.metroverse.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(value = "User API", tags = {"User."})
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	private final JwtTokenProvider jwtTokenProvider;
	private final UserService userService;
	private final OAuthService oAuthService;
	private final Response response;

	@GetMapping("/{type}/callback")
	@ApiOperation(value = "소셜 로그인", notes = "처음 가입한 사용자인지, 기존 사용자인지 구분하여 동작")
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공", response = UserLoginResponse.class),
		@ApiResponse(code = 401, message = "인증 실패", response = Response.class),
		@ApiResponse(code = 404, message = "사용자 없음", response = Response.class),
		@ApiResponse(code = 500, message = "서버 오류", response = Response.class)
	})
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
	@ApiOperation(value = "Reissue", notes = "access token 만료 시 access token, refresh token 재발행")
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

	@GetMapping("/logout")
	@ApiOperation(value = "로그아웃", notes = "쿠키 삭제")
	public ResponseEntity<?> logout(@CookieValue(value = "refresh-token", required = false) Cookie cookie,
		HttpServletResponse res) {
		cookie.setMaxAge(0);
		return response.success();
	}

	@GetMapping("/mypage")
	@ApiOperation(value = "마이페이지", notes = "회원 정보 조회")
	public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String bearerToken) {
		return response.success(userService.getUserInfo(jwtTokenProvider.getUserId(bearerToken)));
	}
}
