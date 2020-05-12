package com.snack.news.exception;

import com.snack.news.exception.base.BadRequestException;
import lombok.Getter;

@Getter
public class TopicBadRequestException extends BadRequestException {
	private final static String BASIC_ERROR_MESSAGE = "잘못된 요청입니다.";
	private final static String CUSTOM_ERROR_MESSAGE = "{%s}의 %s는 부적절한 요청입니다.";

	public TopicBadRequestException(String param, String value) {
		super(String.format(CUSTOM_ERROR_MESSAGE, param, value));
	}

	@Override
	public String getMessage() {
		return BASIC_ERROR_MESSAGE;
	}
}
