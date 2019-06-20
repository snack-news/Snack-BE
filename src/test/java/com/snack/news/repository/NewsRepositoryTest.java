package com.snack.news.repository;

import com.snack.news.domain.News;
import com.snack.news.fixture.NewsTestcase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NewsRepositoryTest extends NewsTestcase {
	@Autowired
	private NewsRepository newsRepository;

	@Test
	public void News를_저장할수있다() {
		long size = newsRepository.count();
		newsRepository.save(mockNews);

		List<News> newsList = newsRepository.findAll();
		assertThat(newsList.size()).isEqualTo(size + 1);
	}

	@Test
	public void News가_해당하는_날짜에_없다면_빈값을_반환한다() {
		newsRepository.save(mockNews);
		LocalDateTime startTime = LocalDateTime.now();
		LocalDateTime endTime = LocalDateTime.now().plusHours(1);

		List<News> newsList = newsRepository.findByCreateAtBetween(startTime, endTime);

		assertThat(newsList.size()).isEqualTo(0);
	}

	@Test
	public void News가_해당하는_날짜에_있다면_리스트를_반환한다() {
		LocalDateTime startTime = LocalDateTime.now();
		LocalDateTime endTime = LocalDateTime.now().plusHours(1);
		newsRepository.save(mockNews);

		List<News> newsList = newsRepository.findByCreateAtBetween(startTime, endTime);

		assertThat(newsList.size()).isEqualTo(1);
		assertThat(newsList.get(0).getCreateAt()).isBefore(endTime).isAfter(startTime);
	}
}