package com.snack.news.util;

import com.snack.news.fixture.NewsTestcase;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class WeekUtilTest extends NewsTestcase {

	private static final LocalDateTime dateOf20190729Monday0000 = LocalDateTime.of(2019, 7, 29, 0, 0, 0);
	private static final LocalDateTime dateOf20190804Sunday1159 = LocalDateTime.of(2019, 8, 4, 11, 59, 59);

	@Test
	public void 월요일에_대한_한주의_시작날과_마지막날을_정상적으로_반환한다() {
		LocalDateTime someMonday = dateOf20190729Monday0000;
		assertThat(someMonday.getDayOfWeek(), equalTo(DayOfWeek.MONDAY));
		assertThat(WeekUtil.getFirstDayOfWeek(someMonday), equalTo(dateOf20190729Monday0000));
		assertThat(WeekUtil.getLastDayOfWeek(someMonday), equalTo(dateOf20190804Sunday1159));
	}

	@Test
	public void 화요일과_금요일_사이에_대한_한주의_시작날과_마지막날을_정상적으로_반환한다() {
		LocalDateTime someday = LocalDateTime.of(2019, 8, 1, 0, 0, 0);
		
		assertThat(WeekUtil.getFirstDayOfWeek(someday), equalTo(dateOf20190729Monday0000));
		assertThat(WeekUtil.getLastDayOfWeek(someday), equalTo(dateOf20190804Sunday1159));
	}

	@Test
	public void 일요일에_대한_한주의_시작날과_마지막날을_정상적으로_반환한다() {
		LocalDateTime someSunday = dateOf20190804Sunday1159;
		assertThat(someSunday.getDayOfWeek(), equalTo(DayOfWeek.SUNDAY));
		assertThat(WeekUtil.getFirstDayOfWeek(someSunday), equalTo(dateOf20190729Monday0000));
		assertThat(WeekUtil.getLastDayOfWeek(someSunday), equalTo(dateOf20190804Sunday1159));
	}
}