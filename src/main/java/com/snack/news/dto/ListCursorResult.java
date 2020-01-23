package com.snack.news.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ListCursorResult<T> {
	private List<T> data;
	private boolean hasNext;

	public boolean isEmpty() {
		return data.isEmpty();
	}
}