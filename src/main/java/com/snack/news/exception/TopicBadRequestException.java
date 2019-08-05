package com.snack.news.exception;

import com.snack.news.exception.base.BadRequestException;

public class TopicBadRequestException extends BadRequestException {
	private final static String BASIC_ERROR_MESSAGE = "잘못된 요청입니다.";
	private final static String CUSTOM_ERROR_MESSAGE = "{%s}의 %s는 부적절한 요청입니다.";

	@Override
	public String getMessage() {
		return BASIC_ERROR_MESSAGE;
	}

	public String getMessage(String paramName, String badValue) {
		return String.format(CUSTOM_ERROR_MESSAGE, paramName, badValue);
	}
}
