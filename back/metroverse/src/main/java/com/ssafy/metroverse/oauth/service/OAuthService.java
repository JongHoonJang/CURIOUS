package com.ssafy.metroverse.oauth.service;

import static com.ssafy.metroverse.user.UserConstant.*;

import javax.transaction.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.metroverse.global.model.Role;
import com.ssafy.metroverse.oauth.auth.KaKaoOAuth2;
import com.ssafy.metroverse.oauth.dto.KakaoToken;
import com.ssafy.metroverse.user.JwtTokenProvider;
import com.ssafy.metroverse.user.domain.User;
import com.ssafy.metroverse.user.dto.TokenRequest;
import com.ssafy.metroverse.user.dto.TokenResponse;
import com.ssafy.metroverse.user.dto.UserJoinRequest;
import com.ssafy.metroverse.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class OAuthService {

	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;
	private final KaKaoOAuth2 kaKaoOAuth2;
	private final UserRepository userRepository;

	private boolean isExistEmail(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

	public TokenResponse login(String email) {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_USER_ERROR_MESSAGE));
		return new TokenResponse(jwtTokenProvider.createToken(email), user.getRefreshToken());
	}

	public void join(UserJoinRequest userJoinRequest) {
		isExistEmail(userJoinRequest.getEmail());
		User user = userRepository.save(User.builder()
			.email(userJoinRequest.getEmail())
			.nickname(userJoinRequest.getNickname())
			.refreshToken(jwtTokenProvider.createRefreshToken())
			.role(Role.ROLE_USER)
			.build());
	}

	public TokenResponse reissue(TokenRequest tokenRequest) {
		// 만료 기간 지났는지 확인
		if (!jwtTokenProvider.validateToken(tokenRequest.getRefreshToken()))
			throw new IllegalArgumentException(INVALID_REFRESH_TOKEN_ERROR_MESSAGE);

		User user = findUserByToken(tokenRequest);

		if (!user.getRefreshToken().equals(tokenRequest.getRefreshToken()))
			throw new IllegalArgumentException(INVALID_REFRESH_TOKEN_ERROR_MESSAGE);

		String accessToken = jwtTokenProvider.createToken(user.getEmail());
		String refreshToken = jwtTokenProvider.createRefreshToken();
		user.updateRefreshToken(refreshToken);
		return new TokenResponse(accessToken, refreshToken);
	}

	public User findUserByToken(TokenRequest tokenRequest) {
		Authentication auth = jwtTokenProvider.getAuthentication(tokenRequest.getAccessToken());
		UserDetails userDetails = (UserDetails)auth.getPrincipal();
		String username = userDetails.getUsername();
		return userRepository.findByEmail(username)
			.orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_EMAIL_ERROR_MESSAGE));
	}

	public TokenResponse kakaoLogin(String authorizedCode) {
		// 카카오 OAuth2를 통해 카카오 사용자 정보 조회
		KakaoToken userInfo = kaKaoOAuth2.getUserInfo(authorizedCode);

		String email = userInfo.getEmail();
		String username = userInfo.getNickname();

		// DB에 중복된 이메일 있는지 확인
		if (!isExistEmail(email)) {
			// 카카오 정보로 회원가입

			UserJoinRequest userJoinRequest = new UserJoinRequest(email, username);
			join(userJoinRequest);
		}

		// 로그인 처리
		return login(email);
	}
}
