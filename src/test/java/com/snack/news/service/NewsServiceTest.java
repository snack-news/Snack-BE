package com.snack.news.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.snack.news.domain.News;
import com.snack.news.dto.NewsDto;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.fixture.NewsTestcase;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceTest extends NewsTestcase {
	@Autowired
	private NewsService newsService;

	@Test
	public void getNewsTest_News_ID로_뉴스를_조회할_수_있다() {
		NewsDto newsDto = NewsDto.builder().title(TEST_TITLE).content(TEST_CONTENT).build();
		NewsDto savedNews = newsService.createNews(newsDto);
		Long id = savedNews.getId();

		News result = newsService.getNews(id);

		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getTitle()).isEqualTo(TEST_TITLE);
		assertThat(result.getContent()).isEqualTo(TEST_CONTENT);
	}

	@Test(expected = NewsNotFoundException.class)
	public void getNewsTest_News_ID가_유효하지않는다면_예외를_반환한다() {
		Long invalidNewsId = 999L;

		newsService.getNews(invalidNewsId);
	}
}