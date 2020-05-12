package com.snack.news.exception.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@AllArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public abstract class ServerException extends RuntimeException {
	public final static String ERROR_CODE = "SERVE_ERROR";
	private String detailMessage;

	public String getErrorCode() {
		return ERROR_CODE;
	}
}
