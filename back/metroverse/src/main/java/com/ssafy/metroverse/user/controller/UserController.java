package com.ssafy.metroverse.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.metroverse.global.common.Response;
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

	@GetMapping("/kakao/callback")
	public ResponseEntity<?> kakaoLogin(@RequestParam(value = "code") String code, HttpServletResponse res) {
		// authorizedCode : 카카오 서버로부터 받은 인가 코드
		TokenResponse tokenResponse = oAuthService.kakaoLogin(code);
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
	
	// public ResponseEntity<?> kakaoCallBack(@RequestBody String code) {
	// 	JsonElement element = JsonParser.parseString(code);
	// 	code = element.getAsJsonObject().get("code").getAsString();
	//
	// 	String accessToken = userService.getKakaoAccessToken(code);
	// 	String email = userService.getEmail(accessToken);
	//
	// 	// 존재하면 바로 로그인
	// 	if(userService.isExistEmail(email)) {
	// 		User user = userService.login(email);
	// 		return response.success(new UserLoginResponse(false, jwtTokenProvider.createToken(String.valueOf(user.getId())), email),
	// 			"이미 가입한 회원입니다.", HttpStatus.OK);
	// 	} else { // 존재하지 않으면 회원가입 페이지로 이동
	// 		return new ResponseEntity<>(new UserLoginResponse(true, null, email), HttpStatus.OK);
	// 	}
	// }
	//
	// @PostMapping("/join")
	// public ResponseEntity<?> kakaoJoinAndLogin(@RequestBody UserJoinRequest userJoinRequest){
	// 	userService.join(userJoinRequest);
	// 	return response.success(jwtTokenProvider.createToken(String.valueOf(userService.login(userJoinRequest.getEmail()).getId())), "회원가입 / 로그인 성공", HttpStatus.OK);
	// }
}
