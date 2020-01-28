package com.snack.news.service;

import com.snack.news.dto.RequestNewsDto;

import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.fixture.NewsFixture;
import com.snack.news.repository.NewsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
		RequestNewsDto newsDtoWithValidDates = mockRequestNewsDto;
		when(newsRepository.findByNewsDto(newsDtoWithValidDates)).thenReturn(mockNewsList);

		newsService.getNewsList(newsDtoWithValidDates);
		verify(newsRepository, times(2)).findByNewsDto(any(RequestNewsDto.class));
	}

	@Test
	@DisplayName("뉴스를 조회시 ID가 부적절하면 예외가 발생한다")
	void getNewsListTestWhenInvalidId() {
		when(newsRepository.findById(anyLong())).thenThrow(NewsNotFoundException.class);
		assertThrows(NewsNotFoundException.class, () -> newsService.getNews(TEST_SOME_ID_LONG));
	}
}