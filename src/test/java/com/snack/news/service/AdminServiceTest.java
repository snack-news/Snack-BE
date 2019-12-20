package com.snack.news.service;

import com.snack.news.domain.news.News;
import com.snack.news.exception.CategoryNotFoundException;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.exception.TagNotFoundException;
import com.snack.news.exception.TopicNotFoundException;
import com.snack.news.fixture.NewsFixture;
import com.snack.news.repository.NewsRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest extends NewsFixture {

	@InjectMocks
	private AdminService adminService;

	@Mock
	private NewsRepository newsRepository;

	@Mock
	private CategoryService categoryService;

	@Mock
	private TopicService topicService;

	@Mock
	private TagService tagService;

	@Test
	@DisplayName("뉴스를 생성할 수 있다")
	public void createNewsTest() {
		adminService.createNews(mockAdminNewsDto);
		verify(newsRepository).save(any(News.class));
	}

	@Test
	@DisplayName("뉴스 생성시 Category id가 부적절하다면 예외가 발생한다")
	public void createNewsTestWhenIllegalCategoryId() {
		when(categoryService.getCategory(any())).thenThrow(CategoryNotFoundException.class);
		assertThrows(CategoryNotFoundException.class, () -> adminService.createNews(mockAdminNewsDto));
	}

	@Test
	@DisplayName("뉴스 생성시 Topic id가 부적절하다면 예외가 발생한다")
	public void createNewsTestWhenIllegalTopicId() {
		when(topicService.getTopicList(mockAdminNewsDto.getTopicNames())).thenThrow(TopicNotFoundException.class);
		assertThrows(TopicNotFoundException.class, () -> adminService.createNews(mockAdminNewsDto));
	}

	@Test
	@DisplayName("뉴스 생성시 Tag id가 부적절하다면 예외가 발생한다")
	public void createNewsTestWhenIllegalTagId() {
		when(tagService.getTagList(mockNewsDto.getTagIds())).thenThrow(TagNotFoundException.class);
		assertThrows(TagNotFoundException.class, () -> adminService.createNews(mockAdminNewsDto));
	}

	@Test
	@DisplayName("뉴스를 수정할 수 있다")
	public void updateNewsTest() {
		final long anyLong = 1L;
		when(newsRepository.findById(anyLong)).thenReturn(Optional.of(mockNews));

		adminService.updateNews(anyLong, mockAdminNewsDto);
		verify(newsRepository).save(any(News.class));
	}

	@Test
	@DisplayName("어드민 페이지에서 뉴스리스트를 페이징처리할 수 있다")
	public void adminNewsPagingTest() {

		List<News> newsList = Arrays.asList(
				News.builder().build(),
				News.builder().build(),
				News.builder().build(),
				News.builder().build(),
				News.builder().build());

		when(newsRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(newsList));

		adminService.getNewsList(1);
		verify(newsRepository).findAll(any(Pageable.class));
	}

	@Test
	@DisplayName("뉴스를 삭제할 수 있다")
	public void deleteNewsTest() {
		adminService.deleteNews(anyLong());
		verify(newsRepository).deleteById(anyLong());
	}

	@Test
	@DisplayName("뉴스를 삭제 요청시 뉴스ID가 존재히지 않는다면 예외가 발생한다")
	public void deleteNewsTestWhenNotExistNewsId() {
		doThrow(new IllegalArgumentException()).when(newsRepository).deleteById(anyLong());
		assertThrows(NewsNotFoundException.class, () -> adminService.deleteNews(anyLong()));
	}
}
