package com.snack.news.service;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.snack.news.domain.News;
import com.snack.news.fixture.NewsTestCase;
import com.snack.news.repository.NewsRepository;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceTest extends NewsTestCase {
	@Autowired
	private NewsService service;

	@Autowired
	private NewsRepository repository;

	@After
	public void cleanup() {
		repository.deleteAll();
	}

	@Test
	public void 시간대_뉴스를_조회할_수_있다() {
		service.createNews(mockNews);
		LocalDateTime startDateTime = LocalDateTime.now().minusHours(1);
		LocalDateTime endDateTime = startDateTime.plusHours(1);

		List<News> result = service.getNews(startDateTime, endDateTime);

		assertThat(result.size()).isNotEqualTo(0);
		result.forEach(news -> assertThat(news.getCreateTime()).isBetween(startDateTime, endDateTime));
	}

	@Test
	public void 시간대_뉴스가_없다면_EmptyList를_반환한다() {
		service.createNews(mockNews);
		LocalDateTime startDateTime = LocalDateTime.now().plusHours(1);
		LocalDateTime endDateTime = startDateTime.plusHours(2);

		List<News> result = service.getNews(startDateTime, endDateTime);

		assertThat(result.size()).isEqualTo(0);
	}
}
