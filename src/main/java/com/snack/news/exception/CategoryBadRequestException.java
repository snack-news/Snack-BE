package com.snack.news.exception;

import com.snack.news.exception.base.BadRequestException;

public class CategoryBadRequestException extends BadRequestException {
	private final static String BASIC_ERROR_MESSAGE = "잘못된 요청입니다.";
	private final static String CUSTOM_ERROR_MESSAGE = "%s값이 필요합니다.";

	@Override
	public String getMessage() {
		return BASIC_ERROR_MESSAGE;
	}

	public String getMessage(String paramName) {
		return String.format(CUSTOM_ERROR_MESSAGE, paramName);
	}
}
