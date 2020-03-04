package com.snack.news.exception;

import com.snack.news.exception.base.NotFoundException;

public class NewsNotFoundException extends NotFoundException {
	private final static String ERROR_MESSAGE = "해당 뉴스를 찾을 수 없습니다.";

	public NewsNotFoundException(Long id) {
		super(id);
	}

	@Override
	public String getMessage() {
		return ERROR_MESSAGE;
	}
}
