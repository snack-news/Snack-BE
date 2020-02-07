package com.snack.news.dto;


import com.snack.news.exception.NewsBadRequestException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Period {
	private LocalDateTime start;
	private LocalDateTime end;

	public Period(LocalDateTime start, LocalDateTime end) {
		this.start = start;
		this.end = end;
	}

	public void validationCheck() {
		if (Objects.isNull(start)) {
			throw new NewsBadRequestException();
		}

		if (end != null && start.isAfter(end)) {
			throw new NewsBadRequestException();
		}
	}
}
