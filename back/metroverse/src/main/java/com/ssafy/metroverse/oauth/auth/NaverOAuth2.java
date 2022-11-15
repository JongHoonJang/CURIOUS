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

import com.ssafy.metroverse.oauth.dto.NaverToken;
import com.ssafy.metroverse.oauth.dto.SocialToken;

@Component
public class NaverOAuth2 implements SocialOAuth2 {

	@Value("${spring.social.naver.url.login}")
	private String NAVER_LOGIN_URL;
	@Value("${spring.social.naver.client_id}")
	private String NAVER_ID;
	@Value("${spring.social.naver.client_secret}")
	private String NAVER_SECRET;
	@Value("${spring.social.naver.redirect}")
	private String NAVER_REDIRECT_URI;
	@Value("${spring.social.naver.state}")
	private String state;

	@Override
	public String getAccessToken(String authorizedCode) {
		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", NAVER_ID);
		params.add("client_secret", NAVER_SECRET);
		params.add("redirect_uri", NAVER_REDIRECT_URI);
		params.add("code", authorizedCode);
		params.add("state", state);

		// HttpHeader 와 HttpBody를 하나의 오브젝트에 담기
		RestTemplate rt = new RestTemplate();
		HttpEntity<MultiValueMap<String, String>> naverTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청하기 - Post 방식으로 - 그리고 Response 변수의 응답받음
		ResponseEntity<String> response = rt.exchange(
			NAVER_LOGIN_URL,
			HttpMethod.POST,
			naverTokenRequest,
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
		HttpEntity<MultiValueMap<String, String>> naverProfileRequest = new HttpEntity<>(headers);

		// Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response = rt.exchange(
			"https://openapi.naver.com/v1/nid/me",
			HttpMethod.POST,
			naverProfileRequest,
			String.class
		);

		JSONObject body = new JSONObject(response.getBody());

		System.out.println(body);

		return new NaverToken(0L,
			body.getJSONObject("response").getString("email"),
			body.getJSONObject("response").getString("name"),
			body.getJSONObject("response").getString("profile_image"));
	}
}
