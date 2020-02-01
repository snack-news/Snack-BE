package com.snack.news.service;

import com.snack.news.dto.RequestInquiryDto;
import com.snack.news.exception.PicksNotFoundException;
import com.snack.news.repository.PicksRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PicksServiceTest {

	@InjectMocks
	private PicksService picksService;

	@Mock
	private PicksRepository picksRepository;

	@ParameterizedTest
	@DisplayName("pick 리스트 요청시 마지막 요청한 pick의 id가 없다면 예외가 발생한다")
	@ValueSource(longs = {1})
	void getPickListTestForInfinityScrolling(final long invalidPickId) {
		RequestInquiryDto dto = RequestInquiryDto.builder().lastNewsId(invalidPickId).build();
		assertThrows(PicksNotFoundException.class, () -> picksService.getPickList(dto));
	}

	@Test
	@DisplayName("pick")
	void getPicksListTest() {
//		when(picksRepository.findById(any())).thenReturn(Optional.of(Pick.builder().build()));
		picksService.getPickList(RequestInquiryDto.builder().build());
		verify(picksRepository).findByPickDto(any(RequestInquiryDto.class));
	}

}