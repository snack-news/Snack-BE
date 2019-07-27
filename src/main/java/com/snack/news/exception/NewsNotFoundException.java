package com.snack.news.exception;

import com.snack.news.exception.base.NotFoundException;

public class NewsNotFoundException extends NotFoundException {
	private final static String BASIC_ERROR_MESSAGE = "The news could not found.";
	@Override
	public String getMessage() {
		return BASIC_ERROR_MESSAGE;
	}
}
