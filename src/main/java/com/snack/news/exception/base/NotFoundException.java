package com.snack.news.exception.base;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public abstract class NotFoundException extends RuntimeException {
	public final static String ERROR_CODE = "NO_RESOURCES";

	public String getErrorCode() {
		return ERROR_CODE;
	}
}
