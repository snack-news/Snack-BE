package com.snack.news.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class WeekUtil {
	public static LocalDateTime getFirstDayWeekOf(LocalDateTime date) {
		return date.minusDays(date.getDayOfWeek().minus(1).getValue()).withHour(0).withMinute(0).withSecond(0);
	}

	public static LocalDateTime getLastDayWeekOf(LocalDateTime date) {
		return date.plusDays(DayOfWeek.SUNDAY.getValue() - date.getDayOfWeek().getValue()).withHour(11).withMinute(59).withSecond(59);
	}
}
