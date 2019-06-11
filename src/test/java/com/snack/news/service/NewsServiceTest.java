package com.snack.news.service;

import com.snack.news.domain.Topic;
import com.snack.news.domain.TopicType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.snack.news.domain.News;
import com.snack.news.dto.NewsDto;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.fixture.NewsTestcase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceTest extends NewsTestcase {
	@Autowired
	private NewsService newsService;

	private static final String TEST_NEWS_TITLE = "test news title";
	private static final String TEST_NEWS_CONTENT = "test news content";

	@Test
	public void 뉴스를_생성할_수_있다() {
		int size = newsService.getNewsList().size();

		NewsDto newsDto = NewsDto.builder().title(TEST_NEWS_TITLE).content(TEST_NEWS_CONTENT).build();
		newsService.createNews(newsDto);

		assertThat(size + 1).isEqualTo(newsService.getNewsList().size());
	}

	@Test
	public void ID로_뉴스를_조회할_수_있다() {
		NewsDto newsDto = NewsDto.builder().title(TEST_TITLE).content(TEST_CONTENT).build();
		NewsDto savedNews = newsService.createNews(newsDto);
		Long id = savedNews.getId();

		News result = newsService.getNews(id);

		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getTitle()).isEqualTo(TEST_TITLE);
		assertThat(result.getContent()).isEqualTo(TEST_CONTENT);
	}

	@Test
	public void Topic_별로_뉴스를_조회할_수_있다() {
		NewsDto newsDto = NewsDto.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.topics(new String[]{"카카오"}).build();

		List<News> newsList = newsService.getNewsList2(newsDto);
		System.out.println(newsList);
		assertThat(newsList.size()).isEqualTo(2);
	}

	@Test(expected = NewsNotFoundException.class)
	public void ID가_유효하지않는다면_예외를_반환한다() {
		Long invalidNewsId = 999L;

		newsService.getNews(invalidNewsId);
	}
}