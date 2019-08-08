package com.snack.news.repository;

import com.snack.news.domain.News;
import com.snack.news.fixture.NewsTestcase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.exparity.hamcrest.date.LocalDateTimeMatchers.after;
import static org.exparity.hamcrest.date.LocalDateTimeMatchers.before;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NewsRepositoryTest extends NewsTestcase {
	@Autowired
	private NewsRepository newsRepository;

	@Test
	@Transactional
	public void News를_저장할수있다() {
		long size = newsRepository.count();
		newsRepository.save(mockNews);

		List<News> newsList = newsRepository.findAll();
		assertThat(newsList.size(), equalTo((int) size + 1));
	}

	@Test
	@Transactional
	public void News가_해당하는_날짜에_없다면_빈값을_반환한다() {
		newsRepository.save(mockNews);
		LocalDateTime startTime = LocalDateTime.now();
		LocalDateTime endTime = LocalDateTime.now().plusHours(1);

		List<News> newsList = newsRepository.findByCreateAtBetween(startTime, endTime);

		assertThat(newsList.size(), equalTo(0));
	}

	@Test
	@Transactional
	public void News가_해당하는_날짜에_있다면_리스트를_반환한다() {
		LocalDateTime startTime = LocalDateTime.now();
		LocalDateTime endTime = LocalDateTime.now().plusHours(1);
		newsRepository.save(mockNews);

		List<News> newsList = newsRepository.findByCreateAtBetween(startTime, endTime);

		assertThat(newsList.size()).isEqualTo(1);
		assertThat(newsList.get(0).getCreateAt(), before(endTime));
		assertThat(newsList.get(0).getCreateAt(), after(startTime));
	}
}