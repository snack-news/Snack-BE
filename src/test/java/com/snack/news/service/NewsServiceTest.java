package com.snack.news.service;

import com.snack.news.dto.NewsDto;
import com.snack.news.exception.NewsBadRequestException;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.fixture.NewsFixture;
import com.snack.news.repository.NewsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceTest extends NewsFixture {

	@InjectMocks
	private NewsService newsService;

	@Mock
	private NewsRepository newsRepository;

	@Test
	public void 뉴스_리스트를_조회할_수_있다() {
		NewsDto newsDtoWithValidDates = mockNewsDto;
		when(newsRepository.findByNewsDto(newsDtoWithValidDates)).thenReturn(any());

		newsService.getNewsList(newsDtoWithValidDates);
	}

	@Test(expected = NewsBadRequestException.class)
	public void 뉴스_리스트를_조회할_때_날짜가_없다면_예외가_발생한다() {
		NewsDto newsDtoWithNoDates = NewsDto.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.build();

		newsService.getNewsList(newsDtoWithNoDates);
	}

	@Test(expected = NewsBadRequestException.class)
	public void 뉴스_리스트를_조회할_때_날짜가_부적절하다면_예외가_발생한다() {
		NewsDto newsDtoWithInvalidDates = NewsDto.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.startDateTime(INVALID_START_DATE)
				.endDateTime(INVALID_END_DATE)
				.build();

		newsService.getNewsList(newsDtoWithInvalidDates);
	}

	@Test
	public void 뉴스를_조회할_수_있다() {
		when(newsRepository.findById(anyLong())).thenReturn(Optional.of(mockNews));
		newsService.getNews(TEST_SOME_ID_LONG);
	}

	@Test(expected = NewsNotFoundException.class)
	public void 뉴스를_조회시_ID가_부적절하면_예외가_발생한다() {
		when(newsRepository.findById(anyLong())).thenThrow(NewsNotFoundException.class);
		newsService.getNews(TEST_SOME_ID_LONG);
	}
}