package com.ssafy.metroverse.user.service;

import static com.ssafy.metroverse.user.UserConstant.*;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.metroverse.user.domain.User;
import com.ssafy.metroverse.user.dto.UserMyPageResponse;
import com.ssafy.metroverse.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService implements UserDetailsService {
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		return userRepository.findByEmail(id)
			.orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND_USER_ERROR_MESSAGE));
	}

	public UserMyPageResponse getUserInfo(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		return UserMyPageResponse.of(user);
	}
}

