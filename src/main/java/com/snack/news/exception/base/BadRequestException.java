package com.snack.news.exception.base;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends IllegalArgumentException {
	public final static String ERROR_CODE = "BAD_REQUEST";

	public String getErrorCode() {
		return ERROR_CODE;
	}
}