package com.snack.news.dto;

import lombok.Getter;

@Getter
public class Wrapper<T> {
	private T data;

	protected Wrapper(T data) {
		this.data = data;
	}

	public static <T> Wrapper<T> valueOf(T data) {
		return new Wrapper<>(data);
	}
}