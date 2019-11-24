package com.snack.news.service;

import com.snack.news.domain.news.News;
import com.snack.news.exception.CategoryNotFoundException;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.exception.TagNotFoundException;
import com.snack.news.exception.TopicNotFoundException;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
	public void 뉴스를_생성할_수_있다() {
		adminService.createNews(mockNewsDto);
	}

	@Test(expected = CategoryNotFoundException.class)
	public void 뉴스_생성시_카테고리ID가_부적절하다면_예외가_발생한다() {
		when(categoryService.getCategory(any())).thenThrow(CategoryNotFoundException.class);

		adminService.createNews(mockNewsDto);
	}

	@Test(expected = TopicNotFoundException.class)
	public void 뉴스_생성시_토픽ID가_부적절하다면_예외가_발생한다() {
		when(topicService.getTopicList(mockNewsDto.getTopicIds())).thenThrow(TopicNotFoundException.class);

		adminService.createNews(mockNewsDto);
	}

	@Test(expected = TagNotFoundException.class)
	public void 뉴스_생성시_태그ID가_부적절하다면_예외가_발생한다() {
		when(tagService.getTagList(mockNewsDto.getTagIds())).thenThrow(TagNotFoundException.class);

		adminService.createNews(mockNewsDto);
	}

	@Test
	public void 뉴스를_수정할_수_있다() {
		final long anyLong = 1L;
		when(newsRepository.findById(anyLong)).thenReturn(Optional.of(mockNews));

		adminService.updateNews(anyLong, mockNewsDto);
	}

	@Test
	public void 어드민_페이지에서_뉴스리스트를_페이징처리할_수_있다() {

		List<News> newsList = Arrays.asList(
				News.builder().build(),
				News.builder().build(),
				News.builder().build(),
				News.builder().build(),
				News.builder().build());

		when(newsRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(newsList));

		adminService.getNewsList(1);
	}

	@Test
	public void 뉴스를_삭제할_수_있다() {
		adminService.deleteNews(anyLong());
		verify(newsRepository).deleteById(anyLong());
	}

	@Test(expected = NewsNotFoundException.class)
	public void 뉴스를_삭제_요청시_뉴스ID가_존재히지_않는다면_예외가_발생한다() {
		doThrow(new IllegalArgumentException()).when(newsRepository).deleteById(anyLong());
		adminService.deleteNews(anyLong());
	}
}
