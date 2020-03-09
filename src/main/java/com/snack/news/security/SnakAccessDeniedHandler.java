package com.snack.news.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snack.news.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SnakAccessDeniedHandler implements AccessDeniedHandler {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
		ErrorResponse message = new ErrorResponse(HttpStatus.FORBIDDEN.toString(), accessDeniedException.getMessage());

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.getOutputStream().println(objectMapper.writeValueAsString(message));
	}
}