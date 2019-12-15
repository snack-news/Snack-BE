package com.snack.news.service;

import com.snack.news.domain.topic.PublishedCorpTopic;
import com.snack.news.domain.topic.Topic;
import com.snack.news.domain.topic.TopicSorting;
import com.snack.news.domain.topic.TopicType;
import com.snack.news.dto.TopicDto;
import com.snack.news.exception.TopicNotFoundException;
import com.snack.news.fixture.TopicFixture;
import com.snack.news.repository.TopicRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TopicServiceTest extends TopicFixture {

	@InjectMocks
	private TopicService topicService;

	@Mock
	private TopicRepository topicRepository;

	@Test
	public void 회사토픽들을_토픽명순으로_조회할_수_있다() {
		Topic testTopic01 = Topic.builder().name(PublishedCorpTopic.YANOLJA.getName()).type(TopicType.CORP).build();
		Topic testTopic02 = Topic.builder().name(PublishedCorpTopic.WOOWA_BROS.getName()).type(TopicType.CORP).build();
		Topic testTopic03 = Topic.builder().name(PublishedCorpTopic.WEMAKEPRICE.getName()).type(TopicType.CORP).build();

		List<Topic> unsortedTopicList = Arrays.asList(testTopic02, testTopic03, testTopic01);
		assertThat(unsortedTopicList, not(contains(testTopic01, testTopic02, testTopic03)));

		when(topicRepository.findAllByTypeIs(eq(TopicType.CORP))).thenReturn(unsortedTopicList);

		List<Topic> resultTopicList = topicService.getTypeTopicList(TopicType.CORP, TopicSorting.NAME);

		assertThat(resultTopicList, contains(testTopic01, testTopic02, testTopic03));
	}

	@Test
	public void 토픽들을_ID순으로_조회할_수_있다() {
		Topic testTopic01 = TopicDto.builder().name(PublishedCorpTopic.COUPANG.getName()).id(1L).type(TopicType.CORP).build().getTopicUpdateEntity();
		Topic testTopic02 = TopicDto.builder().name(PublishedCorpTopic.YANOLJA.getName()).id(2L).type(TopicType.CORP).build().getTopicUpdateEntity();
		Topic testTopic03 = TopicDto.builder().name(PublishedCorpTopic.VIVA_REPUBLICA.getName()).id(3L).type(TopicType.CORP).build().getTopicUpdateEntity();

		List<Topic> unsortedTopicList = Arrays.asList(testTopic02, testTopic03, testTopic01);
		assertThat(unsortedTopicList, not(contains(testTopic01, testTopic02, testTopic03)));

		when(topicRepository.findAllByTypeIs(eq(TopicType.CORP))).thenReturn(unsortedTopicList);

		List<Topic> resultTopicListSortedByID = topicService.getTypeTopicList(TopicType.CORP, TopicSorting.ID);

		assertThat(resultTopicListSortedByID, contains(testTopic01, testTopic02, testTopic03));
	}

	@Test
	public void 노출가능한_토픽들만_조회가_가능하다() {
		Topic publishedTopic1 = Topic.builder().name(PublishedCorpTopic.YANOLJA.getName()).type(TopicType.CORP).build();
		Topic unPublishedTopic = Topic.builder().name("노출이되지않는회사이름").type(TopicType.CORP).build();
		Topic publishedTopic2 = Topic.builder().name(PublishedCorpTopic.WOOWA_BROS.getName()).type(TopicType.CORP).build();
		List<Topic> topicList = Arrays.asList(unPublishedTopic, publishedTopic2, publishedTopic1);
		when(topicRepository.findAllByTypeIs(eq(TopicType.CORP))).thenReturn(topicList);

		List<Topic> resultTopicList = topicService.getTypeTopicList(TopicType.CORP, TopicSorting.NAME);

		assertThat(resultTopicList, contains(publishedTopic1, publishedTopic2));
	}

	@Test
	public void 토픽을_등록할_수_있다() {
		TopicDto topicDto = TopicFixture.TEST_TOPIC_DTO_FOR_CORRECT_REQUEST;
		Topic topicEntityByDto = topicDto.getTopicNewEntity();

		when(topicRepository.save(topicEntityByDto)).thenReturn(topicEntityByDto);
		Topic resultTopic = topicService.createTopic(topicDto);

		assertThat(topicEntityByDto, equalTo(resultTopic));
	}

	@Test(expected = TopicNotFoundException.class)
	public void 토픽_등록시_중복된_이름이라면_예외가_발생한다() {
		TopicDto topicDto = TopicFixture.TEST_TOPIC_DTO_FOR_CORRECT_REQUEST;
		Topic topicEntityByDto = topicDto.getTopicNewEntity();

		when(topicRepository.save(topicEntityByDto)).thenThrow(DataIntegrityViolationException.class);
		topicService.createTopic(topicDto);
	}

	@Test
	public void 토픽을_수정_할_수_있다() {
		final String updatedDataWithTopicName = "UPDATE_TOPIC_NAME";
		long savedTopicId = SOME_SAVED_TEST_TOPIC.getId();

		TopicDto editedTopicDto = TopicDto.builder()
				.id(savedTopicId)
				.name(updatedDataWithTopicName)
				.type(SOME_SAVED_TEST_TOPIC.getType())
				.build();
		Topic editedTopic = editedTopicDto.getTopicUpdateEntity();

		when(topicRepository.findById(savedTopicId)).thenReturn(Optional.of(SOME_SAVED_TEST_TOPIC));
		when(topicRepository.save(editedTopic)).thenReturn(editedTopic);

		Topic resultTopic = topicService.updateTopic(editedTopicDto);
		assertThat(resultTopic.getName(), equalTo(updatedDataWithTopicName));
	}

	@Test(expected = TopicNotFoundException.class)
	public void 수정을_시도한_토픽이_존재하지_않는다면_예외가_발생한다() {
		final String updatedDataWithTopicName = "UPDATE_TOPIC_NAME";
		long savedTopicId = SOME_SAVED_TEST_TOPIC.getId();

		TopicDto editedTopicDto = TopicDto.builder()
				.id(savedTopicId)
				.name(updatedDataWithTopicName)
				.type(SOME_SAVED_TEST_TOPIC.getType())
				.build();

		when(topicRepository.findById(savedTopicId)).thenThrow(TopicNotFoundException.class);

		topicService.updateTopic(editedTopicDto);
	}

	@Test
	public void 토픽ID를_받아_토픽_리스트를_반환한다() {
		final List<Long> validTopicIds = Arrays.asList(1L, 2L);
		final List<Topic> dummyResult = Arrays.asList(DUMMY, DUMMY);
		when(topicRepository.findByIdIn(validTopicIds)).thenReturn(dummyResult);

		List<Topic> realResult = topicService.getTopicList(validTopicIds);
		assertThat(realResult, equalTo(dummyResult));
	}

	@Test(expected = TopicNotFoundException.class)
	public void 토픽ID에_해당하는_토픽이_없으면_예외가_발생한다() {
		final List<Long> invalidTopicIds = Arrays.asList(9999L, 10000L);
		final List<Topic> dummyResult = Collections.singletonList(DUMMY);
		when(topicRepository.findByIdIn(invalidTopicIds)).thenReturn(dummyResult);

		topicService.getTopicList(invalidTopicIds);
	}
}