package com.ssafy.metroverse.oauth.service;

import static com.ssafy.metroverse.user.UserConstant.*;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ssafy.metroverse.global.model.Role;
import com.ssafy.metroverse.global.model.SocialType;
import com.ssafy.metroverse.oauth.auth.SocialOAuth2;
import com.ssafy.metroverse.oauth.dto.SocialToken;
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

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;
	private final List<SocialOAuth2> socialOAuth2List;

	private boolean isExistEmail(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

	public TokenResponse login(String email) {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_USER_ERROR_MESSAGE));
		user.updateRefreshToken(jwtTokenProvider.createRefreshToken(email));
		return new TokenResponse(jwtTokenProvider.createToken(email), user.getRefreshToken());
	}

	public void join(UserJoinRequest userJoinRequest) {
		isExistEmail(userJoinRequest.getEmail());
		User user = userRepository.save(User.builder()
			.email(userJoinRequest.getEmail())
			.nickname(userJoinRequest.getNickname())
			.refreshToken(jwtTokenProvider.createRefreshToken(userJoinRequest.getEmail()))
			.role(Role.ROLE_USER)
			.imageSrc(userJoinRequest.getImageSrc())
			.build());
		logger.info("???????????? ??????!!");
	}

	public TokenResponse reissue(TokenRequest tokenRequest) {

		// ?????? ?????? ???????????? ??????
		// if (!jwtTokenProvider.validateToken(tokenRequest.getRefreshToken()))
		// throw new IllegalArgumentException(INVALID_REFRESH_TOKEN_ERROR_MESSAGE);

		System.out.println("==========before==============================");
		User user = findUserByToken(tokenRequest);
		logger.info(user.getEmail());
		System.out.println("========================================");

		// if (!user.getRefreshToken().equals(tokenRequest.getRefreshToken()))
		// 	throw new IllegalArgumentException(INVALID_REFRESH_TOKEN_ERROR_MESSAGE);

		String accessToken = jwtTokenProvider.createToken(user.getEmail());
		String refreshToken = jwtTokenProvider.createRefreshToken(user.getEmail());
		user.updateRefreshToken(refreshToken);
		logger.info(accessToken);
		return new TokenResponse(accessToken, refreshToken);
	}

	public User findUserByToken(TokenRequest tokenRequest) {
		Authentication auth = jwtTokenProvider.getAuthentication(tokenRequest.getAccessToken());
		UserDetails userDetails = (UserDetails)auth.getPrincipal();
		String username = userDetails.getUsername();
		logger.info(username);
		return userRepository.findByEmail(username)
			.orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_EMAIL_ERROR_MESSAGE));
	}

	public TokenResponse socialLogin(String authorizedCode, SocialType socialType) {
		// OAuth2??? ?????? ????????? ????????? ?????? ??????
		SocialOAuth2 socialOAuth2 = findSocialOauthByType(socialType);
		SocialToken userInfo = socialOAuth2.getUserInfo(authorizedCode);

		String email = userInfo.getEmail();
		String username = userInfo.getNickname();
		String imageSrc = userInfo.getImageSrc();

		// DB??? ????????? ????????? ????????? ??????
		if (!isExistEmail(email)) {
			// ????????? ????????? ????????????

			UserJoinRequest userJoinRequest = new UserJoinRequest(email, username, imageSrc);
			join(userJoinRequest);
		}

		// ????????? ??????
		return login(email);
	}

	private SocialOAuth2 findSocialOauthByType(SocialType socialType) {
		return socialOAuth2List.stream().filter(auth -> auth.type() == socialType)
			.findFirst().orElseThrow(() -> new IllegalArgumentException("????????? ?????? ??????????????????."));
	}

	public void logout(String refreshToken) {
		userRepository.deleteRefreshToken(refreshToken);
	}

	public void deleteUser(String email) {
		userRepository.deleteUserByEmail(email);
	}
}
