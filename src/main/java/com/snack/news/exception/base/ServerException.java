package com.snack.news.exception.base;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public abstract class ServerException extends RuntimeException {
	private final static String ERROR_CODE = "SERVE_ERROR";

	public String getErrorCode() {
		return ERROR_CODE;
	}
}
