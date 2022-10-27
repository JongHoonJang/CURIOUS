package com.ssafy.metroverse.global.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class FormAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws
		IOException, ServletException {
		String errorMsg = "Invalid";

		setDefaultFailureUrl("/login?error=true&exception="+errorMsg);
		super.onAuthenticationFailure(request, response, exception);
	}
}
