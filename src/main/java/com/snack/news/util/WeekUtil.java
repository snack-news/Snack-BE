package com.snack.news.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class WeekUtil {
	public static LocalDateTime getFirstDayOfWeek(LocalDateTime date) {
		return date.minusDays(date.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue()).withHour(0).withMinute(0).withSecond(0);
	}

	public static LocalDateTime getLastDayOfWeek(LocalDateTime date) {
		return date.plusDays(DayOfWeek.SUNDAY.getValue() - date.getDayOfWeek().getValue()).withHour(11).withMinute(59).withSecond(59);
	}
}