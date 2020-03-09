package com.snack.news.exception;

import com.snack.news.exception.base.NotFoundException;

public class TopicNotFoundException extends NotFoundException {
	private final static String ERROR_MESSAGE = "해당 토픽을 찾을 수 없습니다.";

	public TopicNotFoundException(Long id) {
		super(id);
	}

	@Override
	public String getMessage() {
		return ERROR_MESSAGE;
	}
}
