package com.snack.news.exception;

import com.snack.news.exception.base.ServerException;

public class SlackAuthorizationException extends ServerException {
	private final static String errorMessage = "Slack 인증 오류";

	@Override
	public String getMessage() {
		return errorMessage;
	}
}