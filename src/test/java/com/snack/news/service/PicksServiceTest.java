package com.snack.news.service;

import com.snack.news.repository.PicksRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PicksServiceTest {

	@InjectMocks
	private PicksService picksService;

	@Mock
	private PicksRepository picksRepository;

	@ParameterizedTest
	@DisplayName("Admin에서 pick 리스트를 페이지네이션이 정상적으로 동작한다")
	@ValueSource(ints = {1, Integer.MAX_VALUE})
	void getPickListTestOrderByDate(final int page) {
		picksService.getPickPage(page);
		verify(picksRepository).findAll(any(Pageable.class));
	}

	@ParameterizedTest
	@DisplayName("pick리스트의 무한스크롤이 정상적으로 동작한다")
	@ValueSource(longs = {1, 2, Long.MAX_VALUE})
	void getPickListTestForInfinityScrolling(final long lastPickId) {
		final int somePageSize = 10;
		picksService.getPickPage(lastPickId, somePageSize);
		verify(picksRepository).findByIdLessThanOrderByIdDesc(anyLong(), any(Pageable.class));
	}
}