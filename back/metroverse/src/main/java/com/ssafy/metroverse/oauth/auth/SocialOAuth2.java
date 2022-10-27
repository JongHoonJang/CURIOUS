package com.ssafy.metroverse.oauth.auth;

import com.ssafy.metroverse.oauth.domain.SocialType;

public interface SocialOAuth2 {
	String getOauthRedirectURL();

	SocialToken requestAccessToken(String code);

	String getEmailFromToken(SocialToken token);

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
