package com.scb.rest.bookstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Configuration class for Spring Security
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private EntryPoint authEntryPoint;

	@Autowired
	private FailureHandler authFailureHandler;

	@Autowired
	private SuccessHandler authSuccessHandler;

	/**
	 * Get our own implementation of user details service
	 * 
	 * @return User details service
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	};

	/**
	 * Get the password encoder used in Bookstore project
	 * 
	 * @return Password encoder
	 */
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	};

	/**
	 * Get our own UsernamePasswordAuthenticationFilter derivative
	 * 
	 * @return UsernamePasswordAuthenticationFilter derivative
	 * @throws Exception
	 */
	@Bean
	public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {

		CustomAuthenticationFilter authFilter = new CustomAuthenticationFilter();
		authFilter.setAuthenticationManager(authenticationManagerBean());
		authFilter.setAuthenticationSuccessHandler(authSuccessHandler);
		authFilter.setAuthenticationFailureHandler(authFailureHandler);
		authFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));

		return authFilter;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Use our own user details service instead of the default one since we need to
		// use our own database to store our user credentials and use the specific
		// password encoder
		auth.userDetailsService(userDetailsService()).passwordEncoder(getPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()

				// Public resource, no login required
				.antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll() // API doc
				.antMatchers(HttpMethod.POST, "/users").permitAll() // Create user
				.antMatchers(HttpMethod.GET, "/books").permitAll() // Get book list

				// Restricted resource, need to login first
				.anyRequest().authenticated();

		// Add custom authentication filter to support login credential in JSON data
		http.addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		// When an unauthenticated client tries to access a restricted resource.
		// The service should return a 401 HTTP status code. A custom authentication
		// entry point is used to implement that behavior
		http.exceptionHandling().authenticationEntryPoint(authEntryPoint);
	}
}
