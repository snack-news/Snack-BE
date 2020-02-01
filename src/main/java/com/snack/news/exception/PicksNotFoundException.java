package com.snack.news.exception;

import com.snack.news.exception.base.NotFoundException;

public class PicksNotFoundException extends NotFoundException {
	private final static String ERROR_MESSAGE = "해당 Picks를 찾을 수 없습니다.";

	@Override
	public String getMessage() {
		return ERROR_MESSAGE;
	}
}
