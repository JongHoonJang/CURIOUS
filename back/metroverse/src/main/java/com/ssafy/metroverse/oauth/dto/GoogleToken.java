package com.ssafy.metroverse.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GoogleToken implements SocialToken {
	private Long id;
	private String email;
	private String nickname;
	private String imageSrc;
}
