package com.scb.rest.bookstore.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom implementation for SimpleUrlAuthenticationSuccessHandler to handle a
 * custom success event
 */
@Component
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		if ("application/json".equals(request.getHeader("Content-Type"))) {
			// We need to override behavior when authentication success. Upon success,
			// it should returns HTTP 200 OK instead of redirect to other html page.
			response.setStatus(HttpStatus.OK.value());
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

}
