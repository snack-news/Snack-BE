package com.snack.news.dto;

import com.snack.news.domain.picks.Pick;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PicksCursorResult {
	private List<Pick> pickList;
	private boolean hasNext;

	public boolean isEmpty() {
		return pickList.size() == 0;
	}
}
