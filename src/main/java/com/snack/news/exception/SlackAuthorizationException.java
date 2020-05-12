package com.snack.news.exception;

import com.snack.news.exception.base.ServerException;

public class SlackAuthorizationException extends ServerException {
	public final static String errorMessage = "Slack 인증 오류";

	public SlackAuthorizationException(String detailMessage) {
		super(detailMessage);
	}

	@Override
	public String getMessage() {
		return errorMessage;
	}
}