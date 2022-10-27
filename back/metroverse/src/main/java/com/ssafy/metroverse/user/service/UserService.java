package com.ssafy.metroverse.user.service;

import static com.ssafy.metroverse.user.UserConstant.*;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.metroverse.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService implements UserDetailsService {

	// @Value("${oauth2.kakao.restApiKey}")
	// private String kakao_restApiKey;
	// @Value("${oauth2.kakao.redirectUri}")
	// private String kakao_redirectUri;
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		return userRepository.findById(Integer.parseInt(id))
			.orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND_USER_ERROR_MESSAGE));
	}

}

