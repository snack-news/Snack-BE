package com.snack.news.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ListCursorResult<R> {
	private List<R> list;
	private boolean hasNext;

	public boolean isEmpty() {
		return list.isEmpty();
	}
}
