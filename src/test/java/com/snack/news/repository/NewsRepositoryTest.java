package com.snack.news.repository;

import com.snack.news.domain.News;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsRepositoryTest {
	private final String TEST_TITLE = "TEST TITLE";
	private final String TEST_CONTENT = "TEST CONTENT";
	private News mockNews;

	@Autowired
	NewsRepository newsRepository;

	@Before
	public void setUp(){
		mockNews = News.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.build();

	}
	@After
	public void cleanup() {
		newsRepository.deleteAll();
	}

	@Test
	public void 뉴스를_저장하고_확인할수있다() {
		//given
		newsRepository.save(mockNews);

		//when
		List<News> newsList = newsRepository.findAll();

		//then
		News posts = newsList.get(0);
		assertThat(posts.getTitle(), is(TEST_TITLE));
		assertThat(posts.getContent(), is(TEST_CONTENT));
	}

	@Test
	public void 뉴스는_BaseTimeEntity를_상속받아_생성시간이_등록된다() {
		//given
		LocalDateTime now = LocalDateTime.now();
		newsRepository.save(mockNews);

		//when
		List<News> newsList = newsRepository.findAll();

		//then
		News news = newsList.get(0);
		assertTrue(news.getCreateTime().isAfter(now));
	}

	@Test
	public void 뉴스ID를_통해_뉴스객체를_얻을수있다(){
		//given
		newsRepository.save(mockNews);

		//when
		Optional<News> result = newsRepository.findById(mockNews.getId());

		//then
		assertThat(mockNews, equalTo(result.get()));
	}

	@Test
	public void 잘못된뉴스ID를_통해_조회하면_뉴스객체를_얻을수없다(){
		//given
		newsRepository.save(mockNews);
		Long invalidId = mockNews.getId()+1;

		//when
		Optional<News> result = newsRepository.findById(invalidId);

		//then
		assertThat(result, equalTo(Optional.empty()));
	}
}