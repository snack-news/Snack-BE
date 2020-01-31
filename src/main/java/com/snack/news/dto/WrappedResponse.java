package com.snack.news.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class WrappedResponse<T> extends ResponseEntity<Wrapper<T>> {

	public WrappedResponse(HttpStatus status) {
		super(status);
	}

	public WrappedResponse(Wrapper<T> body, HttpStatus status) {
		super(body, status);
	}

	public static <W> WrappedResponse<W> ok(Wrapper<W> body) {
		return new WrappedResponse<>(body, HttpStatus.OK);
	}

	public static <W> WrappedResponse<W> okEmpty() {
		return new WrappedResponse<>(HttpStatus.OK);
	}

	public static <W> WrappedResponse<W> noContents() {
		return new WrappedResponse<>(HttpStatus.NO_CONTENT);
	}
}