package com.ssafy.metroverse.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenResponse {
	String accessToken;
	String refreshToken;
}
