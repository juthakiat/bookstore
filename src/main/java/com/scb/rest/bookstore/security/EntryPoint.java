package com.scb.rest.bookstore.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom implementation for AuthenticationEntryPoint
 */
@Component

public class EntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {

        // By implementing our own authentication entry point, we can tell Spring Security exactly what to do if
        // someone tries to access a protected resource without being authenticated. But we are dealing with
        // web services here, so what we actually want our system to do is: “if the client accesses a resource but is not
        // authenticated, we respond with a 401 Unauthorized status“
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
