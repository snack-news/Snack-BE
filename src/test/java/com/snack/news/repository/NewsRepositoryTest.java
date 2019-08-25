package com.snack.news.repository;

import com.snack.news.domain.category.Category;
import com.snack.news.domain.news.News;
import com.snack.news.domain.tag.Tag;
import com.snack.news.domain.topic.Topic;
import com.snack.news.dto.NewsDto;
import com.snack.news.fixture.NewsFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.snack.news.matcher.ContainsInAnyOrder.containsInAnyOrder;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NewsRepositoryTest extends NewsFixture {
	@Autowired
	private NewsRepository newsRepository;

	@Test
	@Transactional
	public void News를_저장할수있다() {
		int originalSize = (int) newsRepository.count();
		newsRepository.save(mockNews);

		List<News> newsList = newsRepository.findAll();
		assertThat(newsList.size(), equalTo(originalSize + 1));
	}

	@Test
	@Transactional
	public void ID에_해당하는_뉴스를_가져온다() {
		final long validNewsId = 1L;
		News resultNews = newsRepository.findById(validNewsId).orElseThrow(RuntimeException::new);
		assertThat(resultNews.getId(), equalTo(validNewsId));
	}

	@Test
	@Transactional
	public void 해당_카테고리의_뉴스_리스트를_가져온다() {
		final Category category = Category.builder().id(2L).title("커머스").build();

		NewsDto queryNewsDtoWithCategory = NewsDto.builder().categoryId(category.getId()).build();
		List<Long> actualResultNewsIdList = newsRepository.findByNewsDto(queryNewsDtoWithCategory)
				.stream()
				.map(News::getId)
				.collect(toList());

		List<Long> expectedResultNewsList = newsRepository.findAll().stream()
				.filter(n -> n.getCategory().equals(category))
				.map(News::getId)
				.collect(toList());

		assertThat(actualResultNewsIdList, containsInAnyOrder(expectedResultNewsList));
	}

	@Test
	@Transactional
	public void 해당_날짜사이의_뉴스_리스트를_가져온다() {
		final LocalDateTime startDate = LocalDateTime.of(2019, 7, 1, 0, 0);
		final LocalDateTime endDate = LocalDateTime.of(2019, 7, 30, 0, 0);

		NewsDto queryNewsDtoWithDate = NewsDto.builder().startDateTime(startDate).endDateTime(endDate).build();

		List<Long> actualResultNewsIdList = newsRepository.findByNewsDto(queryNewsDtoWithDate)
				.stream()
				.map(News::getId)
				.collect(toList());

		List<Long> expectedResultNewsList = newsRepository.findAll().stream()
				.filter(n -> n.getCreateAt().isBefore(endDate))
				.filter(n -> n.getCreateAt().isAfter(startDate))
				.map(News::getId)
				.collect(toList());

		assertThat(actualResultNewsIdList, containsInAnyOrder(expectedResultNewsList));
	}

	@Test
	@Transactional
	public void 해당_토픽들의_뉴스_리스트를_가져온다() {
		List<Long> testTopicIds = Collections.singletonList(1L);

		NewsDto queryNewsDtoWithDate = NewsDto.builder()
				.topicIds(testTopicIds)
				.build();

		List<Long> actualResultNewsIdList = newsRepository.findByNewsDto(queryNewsDtoWithDate)
				.stream()
				.map(News::getId)
				.collect(toList());

		List<Long> expectedResultNewsList = newsRepository.findAll().stream()
				.filter(topics -> topics.getTopics()
						.stream()
						.map(Topic::getId)
						.anyMatch(testTopicIds::contains))
				.map(News::getId)
				.collect(toList());

		assertThat(actualResultNewsIdList, containsInAnyOrder(expectedResultNewsList));
	}

	@Test
	@Transactional
	public void 해당_태그들의_뉴스_리스트를_가져온다() {
		List<Long> testTagIds = Collections.singletonList(1L);

		NewsDto queryNewsDtoWithDate = NewsDto.builder()
				.tagIds(testTagIds)
				.build();

		List<Long> actualResultNewsIdList = newsRepository.findByNewsDto(queryNewsDtoWithDate)
				.stream()
				.map(News::getId)
				.collect(toList());

		List<Long> expectedResultNewsList = newsRepository.findAll().stream()
				.filter(topics -> topics.getTags()
						.stream()
						.map(Tag::getId)
						.anyMatch(testTagIds::contains))
				.map(News::getId)
				.collect(toList());

		assertThat(actualResultNewsIdList, containsInAnyOrder(expectedResultNewsList));
	}

	@Test
	@Transactional
	public void 여러_조건에_해당하는_뉴스_리스트를_가져온다() {
		final List<Long> testTopicIds = Arrays.asList(1L, 2L);
		final LocalDateTime start = LocalDateTime.of(2019, 7, 1, 0, 0);
		final LocalDateTime end = LocalDateTime.of(2019, 8, 31, 0, 0);
		final Category category = Category.builder().id(2L).title("커머스").build();
		final List<Long> tagIds = Collections.singletonList(1L);

		NewsDto queryNewsDto = NewsDto.builder()
				.startDateTime(start)
				.endDateTime(end)
				.categoryId(category.getId())
				.topicIds(testTopicIds)
				.tagIds(tagIds)
				.build();

		List<Long> actualResultNewsIds = newsRepository.findByNewsDto(queryNewsDto)
				.stream()
				.map(News::getId)
				.collect(toList());

		List<Long> expectedResultNewsIds = newsRepository.findAll()
				.stream()
				.filter(n -> n.getCreateAt().isBefore(end))
				.filter(n -> n.getCreateAt().isAfter(start))
				.filter(n -> n.getCategory().equals(category))
				.filter(topics -> topics.getTopics()
						.stream()
						.map(Topic::getId)
						.anyMatch(testTopicIds::contains))
				.filter(news -> news.getTags()
						.stream()
						.map(Tag::getId)
						.anyMatch(tagIds::contains))
				.map(News::getId)
				.collect(toList());

		assertThat(actualResultNewsIds, containsInAnyOrder(expectedResultNewsIds));
	}
}