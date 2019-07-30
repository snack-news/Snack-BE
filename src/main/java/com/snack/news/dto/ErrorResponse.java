package com.snack.news.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
	private String exceptionCode;
	private String exceptionMessage;
}
