package com.snack.news.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class WrappedResponse<T> {

	private T data;

	public WrappedResponse(T data) {
		this.data = data;
	}

	@SuppressWarnings("unchecked")
	public static <T, R> ResponseEntity<R> ok(T body) {
		return (ResponseEntity<R>) ResponseEntity.ok(new Wrapper(body));
	}

	@AllArgsConstructor
	@Getter
	private static class Wrapper<R> {
		private R data;
	}
}
