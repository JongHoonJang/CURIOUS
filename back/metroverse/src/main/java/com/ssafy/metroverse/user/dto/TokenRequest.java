package com.ssafy.metroverse.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenRequest {
	String accessToken;
	String refreshToken;

}
