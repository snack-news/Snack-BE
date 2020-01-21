package com.snack.news.service;

import com.snack.news.dto.NewsDto;
import com.snack.news.exception.NewsBadRequestException;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.fixture.NewsFixture;
import com.snack.news.repository.NewsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsServiceTest extends NewsFixture {

	@InjectMocks
	private NewsService newsService;

	@Mock
	private NewsRepository newsRepository;

	@Test
	@DisplayName("뉴스 리스트를 조회할 수 있다")
	void getNewsListTest() {
		NewsDto newsDtoWithValidDates = mockNewsDto;
		when(newsRepository.findByNewsDto(newsDtoWithValidDates)).thenReturn(mockNewsList);

		newsService.getNewsList(newsDtoWithValidDates);
		verify(newsRepository, times(2)).findByNewsDto(any(NewsDto.class));
	}

	@Test
	@DisplayName("뉴스 리스트를 조회할 때 날짜가 없다면 예외가 발생한다")
	void getNewsListTestWhenNoneDate() {
		NewsDto newsDtoWithNoDates = NewsDto.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.build();

		assertThrows(NewsBadRequestException.class, () -> newsService.getNewsList(newsDtoWithNoDates));
	}

	@Test
	@DisplayName("뉴스 리스트를 조회할 때 날짜가 부적절하다면 예외가 발생한다")
	void getNewsListTestWhenInvalidDate() {
		NewsDto newsDtoWithInvalidDates = NewsDto.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.startDateTime(INVALID_START_DATE)
				.endDateTime(INVALID_END_DATE)
				.build();

		assertThrows(NewsBadRequestException.class, () -> newsService.getNewsList(newsDtoWithInvalidDates));
	}

	@Test
	@DisplayName("뉴스를 조회할 수 있다")
	void getNewsTest() {
		when(newsRepository.findById(anyLong())).thenReturn(Optional.of(mockNews));
		newsService.getNews(TEST_SOME_ID_LONG);
	}

	@Test
	@DisplayName("뉴스를 조회시 ID가 부적절하면 예외가 발생한다")
	void getNewsListTestWhenInvalidId() {
		when(newsRepository.findById(anyLong())).thenThrow(NewsNotFoundException.class);
		assertThrows(NewsNotFoundException.class, () -> newsService.getNews(TEST_SOME_ID_LONG));
	}
}