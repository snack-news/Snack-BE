package com.snack.news.dto;


import com.snack.news.exception.NewsBadRequestException;
import lombok.Getter;

import java.time.DayOfWeek;
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
		if (Objects.isNull(start) || Objects.isNull(end)) {
			throw new NewsBadRequestException();
		}

		if (isNotMonday(start) && isNotSunday(end)) {
			throw new NewsBadRequestException();
		}

		if (!isBothDatesInOneWeek(start, end) || !isBothDatesInSameMonth(start, end)) {
			throw new NewsBadRequestException();
		}
	}

	private boolean isNotMonday(LocalDateTime startDay) {
		return !startDay.getDayOfWeek().equals(DayOfWeek.MONDAY);
	}

	private boolean isNotSunday(LocalDateTime endDay) {
		return !endDay.getDayOfWeek().equals(DayOfWeek.SUNDAY);
	}

	private static boolean isBothDatesInOneWeek(LocalDateTime start, LocalDateTime end) {
		return end.minusWeeks(1L).isBefore(start);
	}

	private static boolean isBothDatesInSameMonth(LocalDateTime start, LocalDateTime end) {
		return end.getMonth() == start.getMonth();
	}

}
