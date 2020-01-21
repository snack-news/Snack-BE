package com.snack.news.dto;

import com.snack.news.exception.NewsBadRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PeriodTest {

	@Test
	@DisplayName("시작일과 마지막일이 주어지고 시작일보다 마지막일이 나중이다")
	void periodValidationTestWithStartDateAndEndDate() {
		LocalDateTime startOfMonday = LocalDateTime.of(2019, 7, 29, 0, 0);
		LocalDateTime endOfWednesday = LocalDateTime.of(2019, 7, 31, 0, 0);

		 assertDoesNotThrow(() -> new Period(startOfMonday, endOfWednesday).validationCheck());
	}

	@Test
	@DisplayName("시작일만 주어지고 시작일보다 마지막일이 나중이다")
	void periodValidationTestWithStartDate() {
		LocalDateTime startOfMonday = LocalDateTime.of(2019, 7, 29, 0, 0);

		assertDoesNotThrow(() -> new Period(startOfMonday, null).validationCheck());
	}

	@Test
	@DisplayName("시작일이 없다면 예외가 발생한다")
	void periodValidationTestWhenNoneStartDate() {
		LocalDateTime endOfWednesday = LocalDateTime.of(2019, 7, 31, 0, 0);

		assertThrows(NewsBadRequestException.class, () -> new Period(null, endOfWednesday).validationCheck());
	}

	@Test
	@DisplayName("마지막일이 시작일보다 이른경우 예외가 발생한다")
	void periodValidationTestWhenStartDateAfterThenEndDate() {
		LocalDateTime startOfMonday = LocalDateTime.of(2019, 7, 29, 0, 0);
		LocalDateTime endOfWednesday = LocalDateTime.of(2019, 7, 28, 0, 0);

		assertThrows(NewsBadRequestException.class, () -> new Period(startOfMonday, endOfWednesday).validationCheck());
	}
}
