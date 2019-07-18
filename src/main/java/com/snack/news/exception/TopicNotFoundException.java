package com.snack.news.exception;

import com.snack.news.exception.base.NotFoundException;

public class TopicNotFoundException extends NotFoundException {
	private final static String BASIC_ERROR_MESSAGE = "The topic could not be found.";
	@Override
	public String getMessage() {
		return BASIC_ERROR_MESSAGE;
	}
}
