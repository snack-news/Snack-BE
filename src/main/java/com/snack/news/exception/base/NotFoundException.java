package com.snack.news.exception.base;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public abstract class NotFoundException extends RuntimeException {
	private final static String ERROR_CODE = "NO_RESOURCES";
	private Long id;

	public NotFoundException(Long id) {
		this.id = id;
	}

	public String getErrorCode() {
		return ERROR_CODE;
	}
}
