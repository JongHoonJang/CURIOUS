package com.ssafy.metroverse.user.dto;

import java.util.Optional;

import com.ssafy.metroverse.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserMyPageResponse {
	private String email;
	private String nickname;

	public static UserMyPageResponse of(Optional<User> user) {
		return new UserMyPageResponseBuilder()
			.email(user.get().getEmail())
			.nickname(user.get().getNickname())
			.build();
	}
}
