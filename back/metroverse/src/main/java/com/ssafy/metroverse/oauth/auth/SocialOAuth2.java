package com.ssafy.metroverse.oauth.auth;

import com.ssafy.metroverse.global.model.SocialType;
import com.ssafy.metroverse.oauth.dto.SocialToken;

public interface SocialOAuth2 {
	String getAccessToken(String authorizedCode);

	SocialToken getUserInfo(String authorizedCode);

	SocialToken getUserInfoByToken(String accessToken);

	default SocialType type() {
		if (this instanceof GoogleOAuth2)
			return SocialType.GOOGLE;
		else if (this instanceof KaKaoOAuth2)
			return SocialType.KAKAO;
		else if (this instanceof NaverOAuth2)
			return SocialType.NAVER;
		else
			return null;
	}
}
