package com.ssafy.metroverse.oauth.dto;

import com.ssafy.metroverse.global.model.SocialType;
import com.ssafy.metroverse.oauth.auth.GoogleOAuth2;
import com.ssafy.metroverse.oauth.auth.KaKaoOAuth2;
import com.ssafy.metroverse.oauth.auth.NaverOAuth2;

public interface SocialToken {
	Long getId();

	String getEmail();

	String getNickname();

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
