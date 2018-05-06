package com.scb.rest.bookstore.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scb.rest.bookstore.entity.User;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Create a custom filter to intercept the authentication process to read
 * login credential from the JSON instead of form data.
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private String jsonUsername;

	private String jsonPassword;

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		String password = null;
		if ("application/json".equals(request.getHeader("Content-Type"))) {
			password = this.jsonPassword;
		}else{
			password = super.obtainPassword(request);
		}

		return password;
	}

	@Override
	protected String obtainUsername(HttpServletRequest request){
		String username = null;

		if ("application/json".equals(request.getHeader("Content-Type"))) {
			username = this.jsonUsername;
		}else{
			username = super.obtainUsername(request);
		}

		return username;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
		if ("application/json".equals(request.getHeader("Content-Type"))) {
			try {
				StringBuffer sb = new StringBuffer();
				String line = null;

				BufferedReader reader = request.getReader();
				while ((line = reader.readLine()) != null){
					sb.append(line);
				}

				ObjectMapper mapper = new ObjectMapper();
				User user = mapper.readValue(sb.toString(), User.class);

				this.jsonUsername = user.getUsername();
				this.jsonPassword = user.getPassword();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return super.attemptAuthentication(request, response);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
											HttpServletResponse response,
											FilterChain chain,
											Authentication authentication)
			throws IOException, ServletException {

		super.successfulAuthentication(request, response, chain, authentication);
	}
}
