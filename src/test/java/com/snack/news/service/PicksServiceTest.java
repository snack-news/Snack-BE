package com.snack.news.service;

import com.snack.news.repository.PicksRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PicksServiceTest {

	@InjectMocks
	private PicksService picksService;

	@Mock
	private PicksRepository picksRepository;

	@ParameterizedTest
	@DisplayName("pick리스트의 무한스크롤이 정상적으로 동작한다")
	@ValueSource(longs = {1, 2, Long.MAX_VALUE})
	void getPickListTestForInfinityScrolling(final long lastPickId) {
		final int somePageSize = 10;
		when(picksRepository.findByIdLessThanOrderByPublishAtDesc(anyLong(), any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));
		picksService.getPickScrollPage(lastPickId, somePageSize);
		verify(picksRepository).findByIdLessThanOrderByPublishAtDesc(anyLong(), any(Pageable.class));
	}
}