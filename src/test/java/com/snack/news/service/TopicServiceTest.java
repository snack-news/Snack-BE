package com.snack.news.service;

import com.snack.news.domain.topic.PublishedCorpTopic;
import com.snack.news.domain.topic.Topic;
import com.snack.news.domain.topic.TopicSorting;
import com.snack.news.domain.topic.TopicType;
import com.snack.news.dto.TopicDto;
import com.snack.news.exception.TopicNotFoundException;
import com.snack.news.fixture.TopicFixture;
import com.snack.news.repository.TopicRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class TopicServiceTest extends TopicFixture {

	@InjectMocks
	private TopicService topicService;

	@Mock
	private TopicRepository topicRepository;

	@Test
	@DisplayName("회사토픽들을 토픽명순으로 조회할 수 있다")
	void getTopicListTestSortedByTopicName() {
		Topic testTopic01 = Topic.builder().name(PublishedCorpTopic.YANOLJA.getName()).type(TopicType.CORP).build();
		Topic testTopic02 = Topic.builder().name(PublishedCorpTopic.WOOWA_BROS.getName()).type(TopicType.CORP).build();
		Topic testTopic03 = Topic.builder().name(PublishedCorpTopic.WEMAKEPRICE.getName()).type(TopicType.CORP).build();

		List<Topic> unsortedTopicList = Arrays.asList(testTopic02, testTopic03, testTopic01);
		assertThat(unsortedTopicList).doesNotContainSequence(testTopic01, testTopic02, testTopic03);

		when(topicRepository.findAllByTypeIs(eq(TopicType.CORP))).thenReturn(unsortedTopicList);

		List<Topic> resultTopicList = topicService.getTypeTopicList(TopicType.CORP, TopicSorting.NAME);
		assertThat(resultTopicList).containsExactlyInAnyOrder(testTopic01, testTopic02, testTopic03);
	}

	@Test
	@DisplayName("토픽들을 ID순으로 조회할 수 있다")
	void getTopicListTestSortedByTopicId() {
		Topic testTopic01 = TopicDto.builder().name(PublishedCorpTopic.COUPANG.getName()).id(1L).type(TopicType.CORP).build().getTopicUpdateEntity();
		Topic testTopic02 = TopicDto.builder().name(PublishedCorpTopic.YANOLJA.getName()).id(2L).type(TopicType.CORP).build().getTopicUpdateEntity();
		Topic testTopic03 = TopicDto.builder().name(PublishedCorpTopic.VIVA_REPUBLICA.getName()).id(3L).type(TopicType.CORP).build().getTopicUpdateEntity();

		List<Topic> unsortedTopicList = Arrays.asList(testTopic02, testTopic03, testTopic01);
		assertThat(unsortedTopicList).doesNotContainSequence(testTopic01, testTopic02, testTopic03);

		when(topicRepository.findAllByTypeIs(eq(TopicType.CORP))).thenReturn(unsortedTopicList);

		List<Topic> resultTopicListSortedByID = topicService.getTypeTopicList(TopicType.CORP, TopicSorting.ID);

		assertThat(resultTopicListSortedByID).containsExactlyInAnyOrder(testTopic01, testTopic02, testTopic03);
	}

	@Test
	@DisplayName("노출가능한 토픽들만 조회가 가능하다")
	void getTopicListTestOnlyTopicToBeExposure() {
		Topic publishedTopic1 = Topic.builder().name(PublishedCorpTopic.YANOLJA.getName()).type(TopicType.CORP).build();
		Topic unPublishedTopic = Topic.builder().name("노출이되지않는회사이름").type(TopicType.CORP).build();
		Topic publishedTopic2 = Topic.builder().name(PublishedCorpTopic.WOOWA_BROS.getName()).type(TopicType.CORP).build();
		List<Topic> topicList = Arrays.asList(unPublishedTopic, publishedTopic2, publishedTopic1);
		when(topicRepository.findAllByTypeIs(eq(TopicType.CORP))).thenReturn(topicList);

		List<Topic> resultTopicList = topicService.getTypeTopicList(TopicType.CORP, TopicSorting.NAME);

		assertThat(resultTopicList).contains(publishedTopic1, publishedTopic2);
	}

	@Test
	@DisplayName("토픽을 등록할 수 있다")
	void createTopicTest() {
		TopicDto topicDto = TopicFixture.TEST_TOPIC_DTO_FOR_CORRECT_REQUEST;
		Topic topicEntityByDto = topicDto.getTopicNewEntity();

		when(topicRepository.save(topicEntityByDto)).thenReturn(topicEntityByDto);
		Topic resultTopic = topicService.createTopic(topicDto);

		assertEquals(topicEntityByDto, resultTopic);
	}

	@Test
	@DisplayName("토픽 등록시 중복된 이름이라면 예외가 발생한다")
	void createTopicTestWhenDuplicateTopicName() {
		TopicDto topicDto = TopicFixture.TEST_TOPIC_DTO_FOR_CORRECT_REQUEST;
		Topic topicEntityByDto = topicDto.getTopicNewEntity();

		when(topicRepository.save(topicEntityByDto)).thenThrow(DataIntegrityViolationException.class);

		assertThrows(TopicNotFoundException.class, () -> topicService.createTopic(topicDto));
	}

	@Test
	@DisplayName("토픽을 수정 할 수 있다")
	void updateTopicTest() {
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
		assertEquals(resultTopic.getName(), updatedDataWithTopicName);
	}

	@Test
	@DisplayName("수정을 시도한 토픽이 존재하지 않는다면 예외가 발생한다")
	void updateTopicTestWhenNoneExistTopic() {
		final String updatedDataWithTopicName = "UPDATE_TOPIC_NAME";
		long savedTopicId = SOME_SAVED_TEST_TOPIC.getId();

		TopicDto editedTopicDto = TopicDto.builder()
				.id(savedTopicId)
				.name(updatedDataWithTopicName)
				.type(SOME_SAVED_TEST_TOPIC.getType())
				.build();

		when(topicRepository.findById(savedTopicId)).thenThrow(TopicNotFoundException.class);
		assertThrows(TopicNotFoundException.class, () -> topicService.updateTopic(editedTopicDto));
	}

	@Test
	@DisplayName("토픽이름을 받아 토픽 리스트를 반환한다")
	void getTopicListTest() {
		final String topicNameKakao = "카카오";
		final List<String> validTopicNames = Collections.singletonList(topicNameKakao);

		Topic topicKakao = TopicDto.builder().name(topicNameKakao).type(TopicType.CORP).build().getTopicNewEntity();
		final List<Topic> dummyResult = Collections.singletonList(topicKakao);

		when(topicRepository.existsByName(anyString())).thenReturn(true);
		when(topicRepository.findByName(anyString())).thenReturn(topicKakao);

		List<Topic> realResult = topicService.getTopicList(validTopicNames);
		verify(topicRepository, times(0)).save(topicKakao);
		assertThat(realResult).containsExactlyInAnyOrderElementsOf(dummyResult);
	}

	@Test
	@DisplayName("토픽이름에 해당하는 토픽이 없으면 새 토픽을 생성한 후 반환한다")
	void createTopicTestWhenNonExistTopicName() {
		final String newTopicName = "없는 토픽";
		final List<String> validTopicNames = Collections.singletonList(newTopicName);

		Topic newTopic = TopicDto.builder().name(newTopicName).type(TopicType.CORP).build().getTopicNewEntity();

		final List<Topic> dummyResult = Collections.singletonList(newTopic);

		when(topicRepository.existsByName(anyString())).thenReturn(false);

		List<Topic> realResult = topicService.getTopicList(validTopicNames);
		verify(topicRepository, times(1)).save(newTopic);
		assertThat(realResult).containsExactlyInAnyOrderElementsOf(dummyResult);
	}
}