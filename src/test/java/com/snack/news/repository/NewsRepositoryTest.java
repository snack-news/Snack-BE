package com.snack.news.repository;

import com.snack.news.domain.category.Category;
import com.snack.news.domain.news.News;
import com.snack.news.domain.tag.Tag;
import com.snack.news.domain.topic.Topic;
import com.snack.news.dto.RequestInquiryDto;
import com.snack.news.fixture.NewsFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class NewsRepositoryTest extends NewsFixture {

	private static final LocalDateTime TEST_TIME = LocalDateTime.of(2020, 1, 20, 0, 0);
	@Autowired
	private NewsRepository newsRepository;

	@Test
	@DisplayName("News를 저장할수있다")
	@Transactional
	void saveNewsTest() {
		int originalSize = (int) newsRepository.count();
		newsRepository.save(mockNews);

		List<News> newsList = newsRepository.findAll();
		assertThat(newsList.size()).isEqualTo(originalSize + 1);
	}

	@Test
	@DisplayName("ID에 해당하는 뉴스를 가져온다")
	@Transactional
	void getNewsTestById() {
		final long validNewsId = 1L;
		News resultNews = newsRepository.findById(validNewsId).orElseThrow(RuntimeException::new);
		assertThat(resultNews.getId()).isEqualTo(validNewsId);
	}

	@Test
	@DisplayName("해당 카테고리의 뉴스 리스트를 가져온다")
	@Transactional
	void getNewsListTestByCategory() {
		final Category category = Category.builder().id(2L).title("커머스").build();

		RequestInquiryDto queryNewsDtoWithCategory = RequestInquiryDto.builder().categoryId(category.getId()).build();
		List<Long> actualResultNewsIdList = newsRepository.findByNewsDto(queryNewsDtoWithCategory, TEST_TIME)
				.stream()
				.map(News::getId)
				.collect(toList());

		List<Long> expectedResultNewsList = newsRepository.findAll().stream()
				.filter(n -> n.getCategory().equals(category))
				.map(News::getId)
				.collect(toList());

		assertThat(actualResultNewsIdList).containsExactlyInAnyOrderElementsOf(expectedResultNewsList);
	}

	@Test
	@DisplayName("해당 날짜사이의 뉴스 리스트를 업로드예약일 역순으로 정렬하여 가져온다")
	@Transactional
	void getNewsListTestByPeriodInReverseOrderOfNewsId() {
		final LocalDateTime startDate = LocalDateTime.of(2019, 11, 25, 0, 0);
		final LocalDateTime endDate = LocalDateTime.of(2019, 11, 30, 11, 59);

		RequestInquiryDto queryNewsDtoWithDate = RequestInquiryDto.builder().startDateTime(startDate).endDateTime(endDate).build();

		List<Long> actualResultNewsIdList = newsRepository.findByNewsDto(queryNewsDtoWithDate, TEST_TIME)
				.stream()
				.map(News::getId)
				.collect(toList());

		List<Long> expectedResultNewsList = newsRepository.findAll().stream()
				.filter(n -> n.getPublishAt().isBefore(endDate))
				.filter(n -> n.getPublishAt().isAfter(startDate))
				.filter(n -> n.getPublishAt().isBefore(TEST_TIME))
				.sorted(Comparator.comparing(News::getPublishAt).reversed())
				.map(News::getId)
				.collect(toList());

		assertThat(actualResultNewsIdList).containsExactlyInAnyOrderElementsOf(expectedResultNewsList);
	}

	@Test
	@DisplayName("해당 토픽들의 뉴스 리스트를 가져온다")
	@Transactional
	void getNewsListTestByTopic() {
		List<Long> testTopicIds = Collections.singletonList(1L);

		RequestInquiryDto queryNewsDtoWithTopic = RequestInquiryDto.builder()
				.topicIds(testTopicIds)
				.build();

		List<Long> actualResultNewsIdList = newsRepository.findByNewsDto(queryNewsDtoWithTopic, TEST_TIME)
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

		assertThat(actualResultNewsIdList).containsExactlyInAnyOrderElementsOf(expectedResultNewsList);
	}

	@Test
	@DisplayName("해당 태그들의 뉴스 리스트를 가져온다")
	@Transactional
	void getNewsListTestByTag() {
		List<Long> testTagIds = Collections.singletonList(1L);

		RequestInquiryDto queryNewsDtoWithTag = RequestInquiryDto.builder()
				.tagIds(testTagIds)
				.build();

		List<Long> actualResultNewsIdList = newsRepository.findByNewsDto(queryNewsDtoWithTag, TEST_TIME)
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

		assertThat(actualResultNewsIdList).containsExactlyInAnyOrderElementsOf(expectedResultNewsList);
	}

	@Test
	@DisplayName("여러 조건에 해당하는 뉴스 리스트를 가져온다")
	@Transactional
	void getNewsListTest() {
		final List<Long> testTopicIds = Arrays.asList(1L, 2L);
		final LocalDateTime start = LocalDateTime.of(2019, 11, 25, 0, 0);
		final LocalDateTime end = LocalDateTime.of(2019, 11, 30, 0, 0);
		final Category category = Category.builder().id(2L).title("커머스").build();
		final List<Long> tagIds = Collections.singletonList(1L);

		RequestInquiryDto queryNewsDto = RequestInquiryDto.builder()
				.startDateTime(start)
				.endDateTime(end)
				.categoryId(category.getId())
				.topicIds(testTopicIds)
				.tagIds(tagIds)
				.build();

		List<Long> actualResultNewsIds = newsRepository.findByNewsDto(queryNewsDto, TEST_TIME)
				.stream()
				.map(News::getId)
				.collect(toList());

		List<Long> expectedResultNewsIds = newsRepository.findAll()
				.stream()
				.filter(n -> n.getPublishAt().isBefore(end))
				.filter(n -> n.getPublishAt().isAfter(start))
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

		assertThat(actualResultNewsIds).containsExactlyInAnyOrderElementsOf(expectedResultNewsIds);
	}

	@Test
	@DisplayName("뉴스리스트를 페이징 처리할 수 있다")
	@Transactional
	void getAdminNewsListTestWithPaging() {
		final int pageSize = 10;

		Pageable firstPageable = PageRequest.of(0, pageSize);
		Page<News> firstNewsPage = newsRepository.findAll(firstPageable);

		final int totalPage = firstNewsPage.getTotalPages();
		final long totalElements = firstNewsPage.getTotalElements();

		Pageable middlePageable = PageRequest.of(totalPage - 2, pageSize);
		Page<News> middleNewsPage = newsRepository.findAll(middlePageable);

		Pageable lastPageable = PageRequest.of(totalPage - 1, pageSize);
		Page<News> lastNewsPage = newsRepository.findAll(lastPageable);

		assertThat(firstNewsPage.get().count()).isEqualTo((long) pageSize);
		assertThat(middleNewsPage.get().count()).isEqualTo((long) pageSize);
		assertThat(lastNewsPage.get().count()).isEqualTo((totalElements % pageSize));
	}

	@Test
	@DisplayName("어드민에서 뉴스리스트를 생성일 역순으로 정렬할 수 있다")
	@Transactional
	void getAdminNewsListTestInReverseOrderOfCreatedDate() {
		final int pageSize = 5;
		List<News> expectedListFistNewsPage = newsRepository.findAll()
				.stream().sorted(Comparator.comparing(News::getId).reversed()).limit(pageSize).collect(toList());

		long lastNewsId1 = expectedListFistNewsPage.get(0).getId();
		long lastNewsId2 = expectedListFistNewsPage.get(1).getId();
		long lastNewsId3 = expectedListFistNewsPage.get(2).getId();
		long lastNewsId4 = expectedListFistNewsPage.get(3).getId();
		long lastNewsId5 = expectedListFistNewsPage.get(4).getId();

		Pageable pageableDesc = PageRequest.of(0, pageSize, Sort.by(Sort.Direction.DESC, "id"));
		List<News> result = newsRepository.findAll(pageableDesc).getContent();

		assertThat(result.stream().map(News::getId).collect(toList())).contains((lastNewsId1), lastNewsId2, lastNewsId3, lastNewsId4, lastNewsId5);
	}

	@Test
	@DisplayName("뉴스를 삭제할 수 있다")
	@Transactional
	void deleteNewsTest() {
		long originSize = newsRepository.count();
		newsRepository.deleteById(1L);
		assertThat(newsRepository.count()).isEqualTo(originSize - 1);
	}

	@Test
	@DisplayName("뉴스를 수정할 수 있다")
	@Transactional
	public void updateNewsTest() {
		News someNews = newsRepository.getOne(1L);
		final String changedTitle = "This is changed title.";

		someNews.updateNews(
				changedTitle,
				someNews.getContent(),
				someNews.getLink(),
				someNews.getPublishAt(),
				someNews.getCategory(),
				someNews.getTopics(),
				someNews.getTags()
		);

		newsRepository.save(someNews);

		assertThat(newsRepository.getOne(1L).getTitle()).isEqualTo(changedTitle);
	}

	@Test
	@DisplayName("뉴스 개수를 정하여 가져올 수 있다.")
	@Transactional
	void newsLimitSizeTest() {
		final int limitNewsSize = 5;
		RequestInquiryDto queryNewsDto = RequestInquiryDto.builder()
				.limitSize(limitNewsSize)
				.build();

		List<News> actualResultNewsList = newsRepository.findByNewsDto(queryNewsDto, TEST_TIME);
		List<News> originNewsList = newsRepository.findAll();

		assertAll(
				() -> assertThat(originNewsList.size()).isNotEqualTo(limitNewsSize),
				() -> assertThat(actualResultNewsList.size()).isEqualTo(limitNewsSize)
		);
	}
}