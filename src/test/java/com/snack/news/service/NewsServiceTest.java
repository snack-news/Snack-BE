package com.snack.news.service;

import com.snack.news.domain.news.News;
import com.snack.news.dto.NewsDto;
import com.snack.news.exception.*;
import com.snack.news.fixture.NewsFixture;
import com.snack.news.repository.NewsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceTest extends NewsFixture {

	@InjectMocks
	private NewsService newsService;

	@Mock
	private NewsRepository newsRepository;

	@Mock
	private CategoryService categoryService;

	@Mock
	private TopicService topicService;

	@Mock
	private TagService tagService;

	@Test
	public void 뉴스를_생성할_수_있다() {
		when(newsRepository.save(any())).thenReturn(any());
		newsService.createNews(mockNewsDto);
	}

	@Test(expected = CategoryNotFoundException.class)
	public void 뉴스_생성시_카테고리ID가_부적절하다면_예외가_발생한다() {
		when(categoryService.getCategory(any())).thenThrow(CategoryNotFoundException.class);

		newsService.createNews(mockNewsDto);
	}

	@Test(expected = TopicNotFoundException.class)
	public void 뉴스_생성시_토픽ID가_부적절하다면_예외가_발생한다() {
		when(topicService.getTopicList(mockNewsDto.getTopicIds())).thenThrow(TopicNotFoundException.class);

		newsService.createNews(mockNewsDto);
	}

	@Test(expected = TagNotFoundException.class)
	public void 뉴스_생성시_태그ID가_부적절하다면_예외가_발생한다() {
		when(tagService.getTagList(mockNewsDto.getTagIds())).thenThrow(TagNotFoundException.class);

		newsService.createNews(mockNewsDto);
	}

	@Test
	public void 뉴스_리스트를_조회할_수_있다() {
		NewsDto newsDtoWithValidDates = mockNewsDto;
		when(newsRepository.findByNewsDto(newsDtoWithValidDates)).thenReturn(any());

		newsService.getNewsListForUserView(newsDtoWithValidDates);
	}

	@Test(expected = NewsBadRequestException.class)
	public void 뉴스_리스트를_조회할_때_날짜가_없다면_예외가_발생한다() {
		NewsDto newsDtoWithNoDates = NewsDto.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.build();

		newsService.getNewsListForUserView(newsDtoWithNoDates);
	}

	@Test(expected = NewsBadRequestException.class)
	public void 뉴스_리스트를_조회할_때_날짜가_부적절하다면_예외가_발생한다() {
		NewsDto newsDtoWithInvalidDates = NewsDto.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.startDateTime(INVALID_START_DATE)
				.endDateTime(INVALID_END_DATE)
				.build();

		newsService.getNewsListForUserView(newsDtoWithInvalidDates);
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

	@Test
	public void 어드민_페이지에서_뉴스리스트를_페이징처리할_수_있다() {

		List<News> newsList = Arrays.asList(
				News.builder().build(),
				News.builder().build(),
				News.builder().build(),
				News.builder().build(),
				News.builder().build());

		when(newsRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl(newsList));

		newsService.getNewsListForAdmin(1);
	}
}