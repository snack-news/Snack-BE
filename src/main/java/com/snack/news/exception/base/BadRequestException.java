package com.snack.news.exception.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public abstract class BadRequestException extends RuntimeException {
	public final static String ERROR_CODE = "BAD_REQUEST";
	private String detailMessage;

	public BadRequestException(String detailMessage) {
		this.detailMessage = detailMessage;
	}

	public String getErrorCode() {
		return ERROR_CODE;
	}
}