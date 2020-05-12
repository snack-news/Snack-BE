package com.snack.news.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {
	private String exceptionCode;
	private String exceptionMessage;
	private String detailMessage;
}
