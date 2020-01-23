package com.snack.news.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ListCursorResult<T> extends Wrapper<List<T>> {
	private boolean hasNext;

	public ListCursorResult(List<T> data) {
		super(data);
	}

	public boolean isEmpty() {
		return getData().isEmpty();
	}
}