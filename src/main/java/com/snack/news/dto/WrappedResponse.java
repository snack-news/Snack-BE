package com.snack.news.dto;

import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class WrappedResponse<T> {

	private T data;

	public WrappedResponse(T data) {
		this.data = data;
	}

	public static <T> ResponseEntity<Wrapper<T>> ok(T body) {
		return ResponseEntity.ok(new Wrapper<>(body));
	}
}