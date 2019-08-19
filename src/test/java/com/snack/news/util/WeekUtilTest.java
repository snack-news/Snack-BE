package com.snack.news.util;

import com.snack.news.exception.NewsBadRequestException;
import com.snack.news.fixture.NewsTestcase;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class WeekUtilTest extends NewsTestcase {

	@Test(expected = NewsBadRequestException.class)
	public void 날짜_값이_null이면_예외가_발생한다() {
		assertThat(WeekUtil.isBothDatesInOneWeekWithSameMonth(null, null), is(any(Boolean.class)));
	}
  
	@Test
	public void 시작일이_월요일이_아니면_거짓을_반환한다() {
		final LocalDateTime start = LocalDateTime.of(2019, 8, 13, 0, 0);
		final LocalDateTime end = LocalDateTime.of(2019, 8, 14, 0, 0);

		assertThat(WeekUtil.isBothDatesInOneWeekWithSameMonth(start, end), is(false));
	}

	@Test
	public void 두_날짜의_차이가_7일을_초과하면_거짓을_반환한다() {
		final LocalDateTime start = LocalDateTime.of(2019, 8, 12, 0, 0);
		final LocalDateTime end = LocalDateTime.of(2019, 8, 15, 0, 0);

		assertThat(start.getDayOfWeek().equals(DayOfWeek.MONDAY), is(true));
		assertThat(WeekUtil.isBothDatesInOneWeekWithSameMonth(start, end), is(true));
	}

	@Test
	public void 두_날짜의_차이가_7일_미만이고_같은_달이면_참을_반환한다() {
		final LocalDateTime start = LocalDateTime.of(2019, 8, 12, 0, 0);
		final LocalDateTime end = LocalDateTime.of(2019, 8, 14, 0, 0);

		assertThat(start.getDayOfWeek().equals(DayOfWeek.MONDAY), is(true));
		assertThat(WeekUtil.isBothDatesInOneWeekWithSameMonth(start, end), is(true));
	}

	@Test
	public void 두_날짜의_차이가_정확히_7일이고_같은_달이면_참을_반환한다() {
		final LocalDateTime start = LocalDateTime.of(2019, 8, 12, 0, 0);
		final LocalDateTime end = LocalDateTime.of(2019, 8, 18, 0, 0);

		assertThat(start.getDayOfWeek().equals(DayOfWeek.MONDAY), is(true));
		assertThat(WeekUtil.isBothDatesInOneWeekWithSameMonth(start, end), is(true));
	}

	@Test
	public void 두_날짜의_치이가_7일_이하지만_다른_달이면_거짓을_반환한다() {
		final LocalDateTime start = LocalDateTime.of(2019, 8, 26, 0, 0);
		final LocalDateTime end = LocalDateTime.of(2019, 9, 1, 0, 0);

		assertThat(start.getDayOfWeek().equals(DayOfWeek.MONDAY), is(true));
		assertThat(WeekUtil.isBothDatesInOneWeekWithSameMonth(start, end), is(false));
	}
}