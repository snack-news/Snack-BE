package com.snack.news.util;

import com.snack.news.exception.NewsBadRequestException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Objects;

public class WeekUtil {
	public static boolean isBothDatesInOneWeekWithSameMonth(LocalDateTime start, LocalDateTime end) {
		if (Objects.isNull(start) || Objects.isNull(end)) {
			throw new NewsBadRequestException();
		}

		if (!start.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
			return false;
		}

		return isBothDatesInOneWeek(start, end) && isBothDatesInSameMonth(start, end);
	}

	private static boolean isBothDatesInOneWeek(LocalDateTime start, LocalDateTime end) {
		return end.minusWeeks(1L).isBefore(start);
	}

	private static boolean isBothDatesInSameMonth(LocalDateTime start, LocalDateTime end) {
		return end.getMonth() == start.getMonth();
	}
}