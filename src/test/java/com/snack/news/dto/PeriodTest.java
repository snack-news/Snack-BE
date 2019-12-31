package com.snack.news.dto;

import com.snack.news.exception.NewsBadRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PeriodTest {

	@Test
	@DisplayName("두 날짜가 한주 내에 포함되고 같은 달이고 월요일로 시작한다")
	void periodValidationTestWhenDatesWithinOneWeekAndStartMonday() {
		LocalDateTime startOfMonday = LocalDateTime.of(2019, 7, 29, 0, 0);
		LocalDateTime endOfWednesday = LocalDateTime.of(2019, 7, 31, 0, 0);

		assertDoesNotThrow(() -> new Period(startOfMonday, endOfWednesday).validationCheck());
	}

	@Test
	@DisplayName("두 날짜가 한주 내에 포함되고 같은 달이고 일요일로 끝난다")
	void periodValidationTestWhenDatesWithinOneWeekEndSunday() {
		LocalDateTime startOfWednesday = LocalDateTime.of(2019, 8, 1, 0, 0);
		LocalDateTime endOfSunday = LocalDateTime.of(2019, 8, 4, 0, 0);

		assertDoesNotThrow(() -> new Period(startOfWednesday, endOfSunday).validationCheck());
	}

	@Test
	@DisplayName("두 날짜가 한주 내에 포함되고 같은 달이지만 월요일로 시작하지 않고 일요일로 끝나지 않으면 예외가 발생한다")
	void periodValidationTestWhenDatesWithinOneWeekButInvalidBothDate() {
		LocalDateTime start = LocalDateTime.of(2019, 8, 1, 0, 0);
		LocalDateTime endOfSaturday = LocalDateTime.of(2019, 8, 3, 0, 0);

		assertThrows(NewsBadRequestException.class, () -> new Period(start, endOfSaturday).validationCheck());
	}

	@Test
	@DisplayName("두 날짜가 한주 내에 포함되지만 달이 다르면 예외가 발생한다")
	void periodValidationTestWhenDatesWithinOneWeekButDifferentMonth() {
		LocalDateTime startOfMonday = LocalDateTime.of(2019, 7, 29, 0, 0);
		LocalDateTime endOfSunday = LocalDateTime.of(2019, 8, 4, 0, 0);

		assertThrows(NewsBadRequestException.class, () -> new Period(startOfMonday, endOfSunday).validationCheck());
	}

	@Test
	@DisplayName("두 날짜의 차가 일주일이 넘으면 예외가 발생한다")
	void periodValidationTestWhenExceedOneWeek() {
		LocalDateTime startOfMonday = LocalDateTime.of(2019, 7, 29, 0, 0);
		LocalDateTime startOfNextMonday = LocalDateTime.of(2019, 8, 5, 0, 0);

		assertThrows(NewsBadRequestException.class, () -> new Period(startOfMonday, startOfNextMonday).validationCheck());
	}

	@Test
	@DisplayName("날짜 값이 입력되지 않았다면 예외가 발생한다")
	void periodValidationTestWhenNoneValue() {
		assertThrows(NewsBadRequestException.class, () -> new Period(null, null).validationCheck());
	}
}
