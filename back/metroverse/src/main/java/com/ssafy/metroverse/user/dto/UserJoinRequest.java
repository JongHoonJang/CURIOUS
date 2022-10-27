package com.ssafy.metroverse.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinRequest {
	private String email;
	private String nickname;
}
