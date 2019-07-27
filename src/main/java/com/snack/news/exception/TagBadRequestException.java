package com.snack.news.exception;

import com.snack.news.exception.base.BadRequestException;

public class TagBadRequestException extends BadRequestException {
	private final static String BASIC_ERROR_MESSAGE = "잘못된 요청입니다.";

	@Override
	public String getMessage() {
		return BASIC_ERROR_MESSAGE;
	}
}
