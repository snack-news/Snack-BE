package com.snack.news.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snack.news.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SnakAuthenticationEntryPoint implements AuthenticationEntryPoint {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		ErrorResponse message = new ErrorResponse(HttpStatus.UNAUTHORIZED.toString(), authException.getMessage());

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getOutputStream().println(objectMapper.writeValueAsString(message));
	}
}
