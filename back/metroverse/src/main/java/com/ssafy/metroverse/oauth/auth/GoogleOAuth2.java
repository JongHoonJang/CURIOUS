package com.ssafy.metroverse.oauth.auth;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ssafy.metroverse.oauth.dto.GoogleToken;
import com.ssafy.metroverse.oauth.dto.SocialToken;

@Component
public class GoogleOAuth2 implements SocialOAuth2 {

	// @Value("${spring.social.google.scope}")
	// private String GOOGLE_SCOPE;
	@Value("${spring.social.google.url.login}")
	private String GOOGLE_LOGIN_URL;
	@Value("${spring.social.google.client_id}")
	private String GOOGLE_ID;
	@Value("${spring.social.google.client_secret}")
	private String GOOGLE_SECRET;
	@Value("${spring.social.google.redirect}")
	private String GOOGLE_REDIRECT_URI;

	@Override
	public String getAccessToken(String authorizedCode) {
		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", GOOGLE_ID);
		params.add("client_secret", GOOGLE_SECRET);
		params.add("redirect_uri", GOOGLE_REDIRECT_URI);
		params.add("code", authorizedCode);

		// HttpHeader 와 HttpBody를 하나의 오브젝트에 담기
		RestTemplate rt = new RestTemplate();
		HttpEntity<MultiValueMap<String, String>> googleTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청하기 - Post 방식으로 - 그리고 Response 변수의 응답받음
		ResponseEntity<String> response = rt.exchange(
			GOOGLE_LOGIN_URL,
			HttpMethod.POST,
			googleTokenRequest,
			String.class
		);

		// JSON -> 액세스 토큰 파싱
		String tokenJson = response.getBody();
		JSONObject rjson = new JSONObject(tokenJson);
		String accessToken = rjson.getString("access_token");

		return accessToken;
	}

	@Override
	public SocialToken getUserInfo(String authorizedCode) {
		// 1. 인가코드 -> 액세스 토큰
		String accessToken = this.getAccessToken(authorizedCode);
		SocialToken userInfo = this.getUserInfoByToken(accessToken);

		return userInfo;
	}

	@Override
	public SocialToken getUserInfoByToken(String accessToken) {
		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpHeader 와 HttpBody를 하나의 오브젝트에 담기
		RestTemplate rt = new RestTemplate();
		HttpEntity<MultiValueMap<String, String>> googleProfileRequest = new HttpEntity<>(headers);

		// Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response = rt.exchange(
			"https://www.googleapis.com/oauth2/v1/userinfo",
			HttpMethod.GET,
			googleProfileRequest,
			String.class
		);

		JSONObject body = new JSONObject(response.getBody());

		System.out.println(body);

		return new GoogleToken(0L,
			body.getString("email"),
			body.getString("name"),
			body.getString("picture"));
	}
}
